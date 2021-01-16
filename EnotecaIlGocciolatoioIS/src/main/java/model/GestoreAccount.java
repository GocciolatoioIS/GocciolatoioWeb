package model;

import bean.Indirizzo;
import bean.Utente;
import classDAO.IndirizzoDAO;
import classDAO.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GestoreAccount {

    private final UtenteDAO utenteDAO=new UtenteDAO();
    private final IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

    public GestoreAccount() {
    }

    public void gestioneRegistrazione(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

        String username=request.getParameter("username");
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        String nome=request.getParameter("nome");
        String cognome=request.getParameter("cognome");
        String data_nascita= request.getParameter("data");

        String match =  "/^[0-9A-Za-z]+$/";
        String regPassword =  "/^[A-Za-z0-9]+$/";

        Boolean validate=true;
        String msg="";

        if(!request.getParameter("nome").matches(match))
            if(!nome.equals(""))
                System.out.println("nome utente dato corretto");
            else{
                msg="nome non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false;
            }


        if(validate==true) {
            System.out.println("tutti i campi utente sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "Errore durante la compilazione dei campi di Registrazione:");
            view.forward(request,response);
            return;
        }
        //Se tutti i controlli sono stati superati si crea il bean e si inserisce nel database


        Utente test=utenteDAO.doRetrieveByUsernameEmail(username,email);
        String address;

        //Controllo se l'utente è già inserito nel db
        if(test != null ){
            String p="Controllare nome utente o password. E' possibile che l'utente sia già registrato.";
            System.out.println(p);
            request.setAttribute("error",p);
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            view.forward(request,response);
            return;
        }

        Utente test2= utenteDAO.deRetriveUsername(username);
        //Controllo se l'utente è già inserito nel db
        if(test2 != null ){
            String p="Username sia già registrato.";
            System.out.println(p);
            request.setAttribute("error",p);
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            view.forward(request,response);
            return;
        }
        Utente test3= utenteDAO.doRetrieveByEmail(email);
        //Controllo se l'utente è già inserito nel db
        if(test3 != null ){
            String p="Esiste gia' un account associato alla email inserita";
            System.out.println(p);
            request.setAttribute("error",p);
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            view.forward(request,response);
            return;
        }

        //Viene registrato nel db
        Utente u=new Utente();
        u.setEmail(email);
        u.setUsername(username);
        u.setPass(pass);
        u.setRuolo("utente");
        u.setNome(nome);
        u.setCognome(cognome);
        //System.out.println(data_nascita);

        //Gestione data + controllo eta
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(data_nascita);
            u.setData_nascita(date);
        } catch (ParseException e) {
            e.printStackTrace();
            u.setData_nascita(date);
        }
        //System.out.println(date);

        //Controllo età maggiore per reg
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        Calendar today= Calendar.getInstance();
        int curYear= today.get(Calendar.YEAR);
        int curMonth= today.get(Calendar.MONTH);
        int curDay= today.get(Calendar.DAY_OF_MONTH);

        int year= calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);

        int age= curYear- year;
        if(curMonth<month|| (month == curMonth && curDay< day )){
            age--;
        }

        //System.out.println("age"+date+":"+age);

        if(age<=17){
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "Sei minorenne non puoi registrarti a questo sito");
            view.forward(request,response);
            return;
        }


        utenteDAO.doSave(u);
        request.setAttribute("testid",u.getId());

        msg="utente inserito correttamente";
        request.setAttribute("msg",msg);
        u.setData_nascita(new SimpleDateFormat("yyyy-MM-dd").parse(data_nascita));
        u.setIndirizzoList(new ArrayList<Indirizzo>());
        request.getSession().setAttribute("utente", u);

        //Viene rindirizzata alla HomeServlet
        address = "index.html";
        //response.sendRedirect(address);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    public void gestioneAddIndirizzo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user= (Utente) request.getSession().getAttribute("utente");
        if(user==null){
            response.sendRedirect("/index.html");
            return;
        }

        //Settaggio di parametri
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String via = request.getParameter("via");
        String ncivico = request.getParameter("ncivico");
        String nazione = request.getParameter("country");
        String id= request.getParameter("iDUtente");

        Indirizzo newAddress= new Indirizzo();
        newAddress.setNazione(nazione);
        newAddress.setCitta(citta);
        newAddress.setNumCivico(Integer.parseInt(ncivico));
        newAddress.setVia(via);
        newAddress.setCap(Integer.parseInt(cap));


        indirizzoDAO.doSave(newAddress,Integer.parseInt(id));
        user.setIndirizzoList(indirizzoDAO.retriveAllbyID(Integer.parseInt(id)));
        request.getSession().setAttribute("utente", user);

        response.sendRedirect("InformazioniPersonali.jsp");
        return;
    }


    public void gestoreCancellaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param=request.getParameter("id");
        int id=Integer.parseInt(param);


        utenteDAO.deleteUser(id);

        String address="show-users";
        /*RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);*/

        request.setAttribute("var","Cancellazione eseguita con Successo :)");
        //response.sendRedirect(address);
        //dispatcher.forward(request, response);

        response.sendRedirect(address);

    }

    public void gestoreReindirizzamentoLoginOProfilo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = null;
        Cookie[] cookies = null;

        //Effettua un controllo sui cookie, se ne trova allora:
        if(cookies!=null){
            cookies = request.getCookies();
        } else {
            String address;
            HttpSession session = request.getSession();
            Utente user= (Utente) request.getSession().getAttribute("utente");
            session.setMaxInactiveInterval(60*60*24); //setta l'utente loggato per 24 Minuti

            if(user!=null){
                if(user.getRuolo().equals("utente")||user.getRuolo()==null){
                    address="/InformazioniPersonali.jsp";
                    RequestDispatcher dispatcher =request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }
                else
                if(user.getRuolo().equals("amministratore")){
                    address="/ProfiloAmministratore.jsp";
                    RequestDispatcher dispatcher =request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }

            } else {
                address = "/LoginRegistrazione.jsp";
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        }
    }

    public void gestoreLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        //Se viene cliccato su accedi
        if (action == null) {
            String username = request.getParameter("username");
            String password = request.getParameter("pass");
            String address;
            Utente utente = null;
            if (username != null && password != null) {
                //Cerchiamo nel db un utente con quelle credenziali
                utente = utenteDAO.doRetrieveByUsernamePassword(username, password);
            }


            //SE l'utente non viene trovato
            if (utente == null) {
                String s = "nada";
                request.setAttribute("error", "Utente non presente nel sistema. Ricontrolla i campi");
                address = "/LoginRegistrazione.jsp";
                //response.sendRedirect(address);
                System.out.println(s);
                RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
                HttpSession currentSession = request.getSession();
                view.forward(request, response);
                return;
            }

            //L'utente è stato trovato
            List<Indirizzo> list = indirizzoDAO.retriveAllbyID(utente.getId());
            utente.setIndirizzoList(list);
            //List<MetodoPagamento> listMp= metodoPagamentoDao.doRetriveByUserID(utente.getId());
            request.getSession().setAttribute("utente", utente);

            Cookie cookie = new Cookie("key", "value");
            cookie.setMaxAge(60);
            response.addCookie(cookie);

            //Viene rindirizzata alla HomeServlet
            address = "index.html";
            response.sendRedirect(address);
        }
    }

    public void gestoreLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("utente");
        String address;

        address = "index.html";
        response.sendRedirect(address);

    }

    public void gestoreModificaIndirizzo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Utente user = (Utente) request.getSession().getAttribute("utente");
        if (user == null) {
            response.sendRedirect("/index.html");
            return;
        }

        if (action.equals("redirect")) {
            System.out.println("sono entrato");
            int id = Integer.parseInt(request.getParameter("id"));
            Indirizzo oldIndirizzo = indirizzoDAO.retriveByID(id);
            System.out.println(oldIndirizzo.toString());
            request.setAttribute("oldIndirizzo", oldIndirizzo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ModificaIndirizzo.jsp");
            dispatcher.forward(request, response);

        } else if (action.equals("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));
            indirizzoDAO.removeInd(id);
            user.setIndirizzoList(indirizzoDAO.retriveAllbyID(user.getId()));
            request.getSession().setAttribute("utente", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("InformazioniPersonali.jsp");
            dispatcher.forward(request, response);

        } else if (action.equals("modInd")) {


            //Settaggio di parametri
            String citta = request.getParameter("citta");
            String cap = request.getParameter("cap");
            String via = request.getParameter("via");
            String ncivico = request.getParameter("ncivico");
            String nazione = request.getParameter("country");
            String id = request.getParameter("iDUtente");
            String idInd = request.getParameter("idInd");

            System.out.println(idInd + "," + via + "," + citta + "," + id);

            Indirizzo newAddress = new Indirizzo();
            newAddress.setNazione(nazione);
            newAddress.setCitta(citta);
            newAddress.setNumCivico(Integer.parseInt(ncivico));
            newAddress.setVia(via);
            newAddress.setCap(Integer.parseInt(cap));
            newAddress.setId(Integer.parseInt(idInd));

            indirizzoDAO.doUpdate(newAddress);
            user.setIndirizzoList(indirizzoDAO.retriveAllbyID(Integer.parseInt(id)));
            request.getSession().setAttribute("utente", user);

            response.sendRedirect("InformazioniPersonali.jsp");
            return;
        }
    }

    public void gestoreModificaUtente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente user= (Utente) request.getSession().getAttribute("utente");
        int id = user.getId();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String ruolo=request.getParameter("ruolo");


        Utente u=new Utente();
        u.setId(id);
        u.setEmail(email);
        u.setUsername(username);
        u.setNome(nome);
        u.setCognome(cognome);
        u.setRuolo(ruolo);

        UtenteDAO userDao=new UtenteDAO();
        userDao.doUpdate(u);

        u=userDao.retriveById(u.getId());
        u.setIndirizzoList(indirizzoDAO.retriveAllbyID(user.getId()));

        request.getSession().setAttribute("utente",u );


        if(u.getRuolo().equals("utente")){
            String address = "/InformazioniPersonali.jsp";
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        String address = "/ProfiloAmministratore.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreModificaUtenteByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Utente user= (Utente) request.getSession().getAttribute("utente");

        String id = (String) request.getParameter("id");
        System.out.println((id));

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String via = request.getParameter("via");
        String ncivico = request.getParameter("ncivico");
        String ruolo=request.getParameter("ruolo");

        String regUsername =  "/^[0-9A-Za-z]+$/";
        String regString =  "/^[A-Za-z]+$/";
        String regNum =  "/^[0-9]+$/";
        String regPassword =  "/^[A-Za-z]+$/";
        Boolean validate=true;

        if(!request.getParameter("username").matches(regUsername)) {
            System.out.println("nome dato corretto");
        }else{ validate=false; }
        if(!request.getParameter("citta").matches(regString)) {
            System.out.println("nome dato corretto");
        }else{ validate=false; }
        if(!request.getParameter("cap").matches(regNum)) {
            System.out.println("nome dato corretto");
        }else{ validate=false; }
        if(!request.getParameter("via").matches(regString)) {
            System.out.println("nome dato corretto");
        }else{ validate=false; }
        if(!request.getParameter("ncivico").matches(regUsername)) {
            System.out.println("nome dato corretto");
        }else{ validate=false; }


        if(validate==true) {
            System.out.println("tutti i campi sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("ModificaUtenteByAdmin.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute("error", "error");
            view.forward(request,response);
            return;
        }



        Utente u=new Utente();
        UtenteDAO userDao=new UtenteDAO();

        u.setId(Integer.parseInt(id));
        u.setEmail(email);
        u.setUsername(username);
        u.setRuolo(ruolo);

        userDao.doUpdate(u);

        String address = "ProfiloAmministratore.jsp";

        response.sendRedirect(address);
    }

    public void gestoreMostraUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO userdao=new UtenteDAO();
        ArrayList<Utente> lista=userdao.retriveAll();

        request.setAttribute("lista",lista);

        String address = "/ListaUtenti.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreTrovaUtenteByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param=request.getParameter("id");
        int id=Integer.parseInt(param);

        UtenteDAO udao=new UtenteDAO();
        Utente u=udao.retriveById(id);
        request.setAttribute("user1",u);
        String address="/ModificaUtenteByAdmin.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }


}
