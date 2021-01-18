package model.service;

import model.bean.Indirizzo;
import model.bean.Utente;
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

        String match =  "^[0-9A-Za-z]+$";
        String matchData =  "^[0-9^-]+$";


        Boolean validate=true;
        String msg="";

        if(!request.getParameter("nome").matches(match)){
            if(!nome.equals(""))
                System.out.println("nome utente dato corretto");
            else{
                msg="nome non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false;
            }
        }

        if(!request.getParameter("cognome").matches(match)) {
            if (!cognome.equals(""))
                System.out.println("cognome utente dato corretto");
            else {
                msg = "cognome non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }
        }


            if (!pass.equals(""))
                System.out.println("password utente dato corretto");
            else {
                msg = "password non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }


        if(!request.getParameter("email").matches(match)) {
            if (!email.equals(""))
                System.out.println("email utente dato corretto");
            else {
                msg = "email non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }
        }


            if (!data_nascita.equals(""))
                System.out.println("data nascita utente dato corretto");
            else {
                msg = "data nascita non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }


        if(!request.getParameter("username").matches(matchData)) {
            if (!username.equals(""))
                System.out.println("Username utente dato corretto");
            else {
                msg = "username non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }
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
        //Se tutti i controlli sono stati superati si crea il model.bean e si inserisce nel database


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


        Boolean validate=true;
        String msg="";

        if(!citta.equals(""))
                System.out.println("Citta indirizzo dato corretto");
        else{
            msg="citta indirizzo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false;
        }

        if(!cap.equals(""))
            System.out.println("Cap indirizzo dato corretto");
        else{
            msg="cap indirizzo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false;
        }

        if(!via.equals(""))
            System.out.println("Via indirizzo dato corretto");
        else{
            msg="via indirizzo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false;
        }

        if(!ncivico.equals(""))
            System.out.println("numero civico indirizzo dato corretto");
        else{
            msg="numero civico indirizzo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false;
        }

        if(!nazione.equals(""))
            System.out.println("nazione indirizzo dato corretto");
        else{
            msg="nazione indirizzo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false;
        }

        if(validate==true) {
            System.out.println("tutti i campi indirizzo sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("InformazioniPersonali.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "Errore durante la compilazione dei campi di Registrazione:");
            view.forward(request,response);
            return;
        }

        Indirizzo newAddress= new Indirizzo();
        newAddress.setNazione(nazione);
        newAddress.setCitta(citta);
        newAddress.setNumCivico(Integer.parseInt(ncivico));
        newAddress.setVia(via);
        newAddress.setCap(Integer.parseInt(cap));


        indirizzoDAO.doSave(newAddress,Integer.parseInt(id));
        user.setIndirizzoList(indirizzoDAO.retriveAllbyID(Integer.parseInt(id)));

        request.getSession().setAttribute("utente", user);
        request.getSession().setAttribute("errorTest","Indirizzo inserito");
        request.getSession().setAttribute("idTest",newAddress.getId());
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
                    request.setAttribute("errorTest","utente area");
                    address="/InformazioniPersonali.jsp";
                    RequestDispatcher dispatcher =request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }
                else
                if(user.getRuolo().equals("amministratore")){
                    request.setAttribute("errorTest","admin area");
                    address="/ProfiloAmministratore.jsp";
                    RequestDispatcher dispatcher =request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }

            } else {
                request.setAttribute("errorTest","login area");
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
                request.setAttribute("errorTest","utente non trovato");

                address = "/LoginRegistrazione.jsp";
                //response.sendRedirect(address);
                //System.out.println(s);
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

            request.setAttribute("errorTest","utente  trovato");

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
            String msg;
            boolean validate = true;

            if (!citta.equals(""))
                System.out.println("citta indirizzo corretto");
            else {
                msg = "citta non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }

            if (!cap.equals(""))
                System.out.println("cap indirizzo dato corretto");
            else {
                msg = "cap non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }

            if (!via.equals(""))
                System.out.println("via indirizzo dato corretto");
            else {
                msg = "via non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }

            if (!ncivico.equals(""))
                System.out.println("numero civico indirizzo dato corretto");
            else {
                msg = "numero civico non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }

            if (!nazione.equals(""))
                System.out.println("nazione indirizzo dato corretto");
            else {
                msg = "nazione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest", msg);
                validate = false;
            }

            if(validate==true) {
                System.out.println("tutti i campi utente sono giusti");
            } else {
                RequestDispatcher view = request.getRequestDispatcher("InformazioniPersonali.jsp");/*dove inoltro il form*/
                HttpSession currentSession = request.getSession();
                request.setAttribute("error", "Errore durante la compilazione dei campi modifica indirizzo:");
                view.forward(request,response);
                return;
            }

            //System.out.println(idInd + "," + via + "," + citta + "," + id);

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
            request.setAttribute("errorTest","indirizzo modificato");
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

        String msg;
        Boolean validate=true;

        if (!username.equals(""))
            System.out.println("username dato corretto");
        else {
            msg = "username non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!email.equals(""))
            System.out.println("email dato corretto");
        else {
            msg = "email non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!nome.equals(""))
            System.out.println("nome dato corretto");
        else {
            msg = "nome non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!cognome.equals(""))
            System.out.println("cognome dato corretto");
        else {
            msg = "cognome non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }


        if(validate==true) {
            System.out.println("tutti i campi utente sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("InformazioniPersonali.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "Errore durante la compilazione dei campi modifica indirizzo:");
            view.forward(request,response);
            return;
        }


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
            request.setAttribute("errorTest","modifica utente successo");
            String address = "/InformazioniPersonali.jsp";
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        request.setAttribute("errorTest","modifica utente successo");
        String address = "/ProfiloAmministratore.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreModificaUtenteByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String s= request.getParameter("id");

        int id = 0;

        if(!s.equals(""))
           id=Integer.parseInt(s);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String ruolo=request.getParameter("ruolo");

        String msg;
        Boolean validate=true;

        if (!s.equals(""))
            System.out.println("id dato corretto");
        else {
            msg = "id non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!username.equals(""))
            System.out.println("username dato corretto");
        else {
            msg = "username non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!email.equals(""))
            System.out.println("email dato corretto");
        else {
            msg = "email non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!nome.equals(""))
            System.out.println("nome dato corretto");
        else {
            msg = "nome non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!cognome.equals(""))
            System.out.println("cognome dato corretto");
        else {
            msg = "cognome non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }

        if (!ruolo.equals(""))
            System.out.println("ruolo dato corretto");
        else {
            msg = "ruolo non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest", msg);
            validate = false;
        }


        if(validate==true) {
            System.out.println("tutti i campi utente sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("InformazioniPersonali.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "Errore durante la compilazione dei campi modifica indirizzo:");
            view.forward(request,response);
            return;
        }


        Utente u=new Utente();
        u.setId(id);
        u.setEmail(email);
        u.setUsername(username);
        u.setNome(nome);
        u.setCognome(cognome);
        u.setRuolo(ruolo);

        UtenteDAO userDao=new UtenteDAO();
        userDao.doUpdate(u);

        ArrayList<Utente> list=userDao.retriveAll();
        request.setAttribute("lista",list);

        request.setAttribute("errorTest","modifica utente successo admin");
        String address = "/ListaUtenti.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreMostraUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO userdao=new UtenteDAO();
        ArrayList<Utente> lista=userdao.retriveAll();

        request.setAttribute("lista",lista);

        String address = "/ListaUtenti.jsp";
        request.setAttribute("errorTest","tutti gli utenti presi");
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

        request.setAttribute("errorTest","utente trovato");
        String address="/ModificaUtenteByAdmin.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }


}
