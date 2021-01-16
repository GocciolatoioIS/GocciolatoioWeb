package model;

import bean.Prodotto;
import classDAO.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GestoreProdotto {

    private  final ProdottoDAO prodottoDAO=new ProdottoDAO();

    public GestoreProdotto() {
    }

    public void gestoreAddProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Settaggio di vari parametri tramite request.
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String tipo = request.getParameter("tipo");
        String prezzo = request.getParameter("prezzo");
        Double pre=Double.parseDouble(prezzo);
        String sconto = request.getParameter("sconto");
        Double sc=Double.parseDouble(sconto);
        String immagine = request.getParameter("immagine");
        String annata = request.getParameter("anno");
        int anno=Integer.parseInt(annata);
        String regione = request.getParameter("regione");
        String gradazione=request.getParameter("gradazione");
        int grad=Integer.parseInt(gradazione);
        String formato=request.getParameter("formato");
        int forma=Integer.parseInt(formato);
        String quantita_magazino=request.getParameter("quantita_magazzino");
        int qnt=Integer.parseInt(quantita_magazino);
        String nome_categoria = request.getParameter("nome_categoria");

        String regString =  "/^[A-Za-z]+$/";
        String regNum =  "/^[0-9]+$/";
        Boolean validate=true;

        //!request.getParameter("nome").equals("")||
        String msg="";
        if(!request.getParameter("nome").matches(regString))
            if(!nome.equals(""))
                System.out.println("nome dato corretto");
            else{
                msg="nome non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("tipo").matches(regString))
            if(!tipo.equals(""))
                System.out.println("tipo dato corretto");
            else{
                msg="tipo non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("descrizione").matches(regString))
            if(!descrizione.equals(""))
                System.out.println("descrizione dato corretto");
            else{
                msg="descrizione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("prezzo").matches(regString))
            if(!(pre==0))
                System.out.println("prezzo dato corretto");
            else{
                msg="prezzo non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("sconto").matches(regString))
            if(!(sc==0))
                System.out.println("sconto dato corretto");
            else{
                msg="sconto non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if((!immagine.equals("")))
                System.out.println("immagine dato corretto");
            else{
                msg="immagine non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("anno").matches(regNum))
            if(!(anno ==0))
                System.out.println("anno dato corretto");
            else{
                msg="anno non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("regione").matches(regString))
            if(!regione.equals(""))
                System.out.println("regione dato corretto");
            else{
                msg="regione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("gradazione").matches(regString))
            if(!(grad==0))
                System.out.println("gradazione dato corretto");
            else{
                msg="gradazione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("formato").matches(regString))
            if(!(forma ==0))
                System.out.println("formato dato corretto");
            else{
                msg="formato non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("quantita_magazzino").matches(regString))
            if(!(qnt==0))
                System.out.println("quantita dato corretto");
            else{
                msg="quantita_magazzino non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("nome_categoria").matches(regString))
            if(!nome_categoria.equals(""))
                System.out.println("nome_categoria dato corretto");
            else{
                msg="nome_categoria non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(validate==true) {
            System.out.println("tutti i campi sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("AggiungiProdotto.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute("error", "Errore nell'aggiunta del prodotto");
            view.forward(request,response);
            return;
        }


        Prodotto p=new Prodotto();
        p.setNome(nome);
        p.setDescrizione(descrizione);
        p.setTipo(tipo);
        p.setPrezzo(pre);
        p.setSconto(sc);
        p.setImmagine(immagine);
        p.setAnno(anno);
        p.setRegione(regione);
        p.setGradazione(grad);
        p.setFormato(forma);
        p.setQuantita_magazzino(qnt);
        p.setNome_categoria(nome_categoria);
        prodottoDAO.doSave(p);//Viene richiamato il metodo doSave per rendere i dati persistenti.

        request.setAttribute("testid",p.getId());
        String address = "/index.html";
        msg="Prodotto inserito con successo";
        request.setAttribute("errorTest",msg);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreCancellaProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String param=request.getParameter("id");
        int id=Integer.parseInt(param);

        ProdottoDAO udao=new ProdottoDAO();
        udao.deleteProduct(id);

        String address="show-all-products";

        String msg="Prodotto eliminato con successo";
        request.setAttribute("errorTest",msg);
        request.setAttribute("var","Cancellazione eseguita con Successo :)");
        response.sendRedirect(address);
    }

    public void gestoreCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Viene ripreso il parametro della category, e controllato a quale categoria corrisponde
         */
        String category = request.getParameter("category");
        String address = null;
        String categoria=null;
        if (category.equals("vini")) {
            categoria = "Vino";
            request.setAttribute("category", categoria);
        }
        if (category.equals("spumanti")) {
            categoria = "Spumanti";
            request.setAttribute("category", categoria);
        }
        if (category.equals("champagne")) {
            categoria = "Champagne";
            request.setAttribute("category", categoria);
        }
        if (category.equals("birre")) {
            categoria = "Birra";
            request.setAttribute("category", categoria);
        }
        if (category.equals("superalcolici")) {
            categoria = "Superalcolici";
            request.setAttribute("category", categoria);
        }
        if (category.equals("amari")) {
            categoria = "Amari";
            request.setAttribute("category", categoria);
        }
        if (category.equals("cibo")) {
            categoria = "Cibo";
            request.setAttribute("category", categoria);
        }


        List<Prodotto> prodotti = prodottoDAO.retriveCategory(categoria);
        request.setAttribute("prodotti", prodotti);
        address = "/Categoria.jsp"; //Dopodichè viene reindirizzata la pagina

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreModificaProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String anno = request.getParameter("anno");
        String regione = request.getParameter("regione");
        String gradazione = request.getParameter("gradazione");
        String formato = request.getParameter("formato");
        String quantita_magazzino = request.getParameter("quantita_magazzino");
        String tipo = request.getParameter("tipo");
        String prezzo = request.getParameter("prezzo");
        String sconto = request.getParameter("sconto");
        String immagine = request.getParameter("immagine");
        */
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String tipo = request.getParameter("tipo");
        String prezzo = request.getParameter("prezzo");
        Double pre=Double.parseDouble(prezzo);
        String sconto = request.getParameter("sconto");
        Double sc=Double.parseDouble(sconto);
        String immagine = request.getParameter("immagine");
        String annata = request.getParameter("anno");
        int anno=Integer.parseInt(annata);
        String regione = request.getParameter("regione");
        String gradazione=request.getParameter("gradazione");
        int grad=Integer.parseInt(gradazione);
        String formato=request.getParameter("formato");
        int forma=Integer.parseInt(formato);
        String quantita_magazino=request.getParameter("quantita_magazzino");
        int qnt=Integer.parseInt(quantita_magazino);
        String nome_categoria = request.getParameter("nome_categoria");
        String regString = "/^[A-Za-z]+$/";
        String regNum = "/^[0-9]+$/";

        Boolean validate = true;

        String msg="";
        if(!request.getParameter("nome").matches(regString))
            if(!nome.equals(""))
                System.out.println("nome dato corretto");
            else{
                msg="nome non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("tipo").matches(regString))
            if(!tipo.equals(""))
                System.out.println("tipo dato corretto");
            else{
                msg="tipo non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("descrizione").matches(regString))
            if(!descrizione.equals(""))
                System.out.println("descrizione dato corretto");
            else{
                msg="descrizione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("prezzo").matches(regString))
            if(!(pre==0))
                System.out.println("prezzo dato corretto");
            else{
                msg="prezzo non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("sconto").matches(regString))
            if(!(sc==0))
                System.out.println("sconto dato corretto");
            else{
                msg="sconto non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if((!immagine.equals("")))
            System.out.println("immagine dato corretto");
        else{
            msg="immagine non corretto";
            System.out.println(msg);
            request.setAttribute("errorTest",msg);
            validate=false; }

        if(!request.getParameter("anno").matches(regNum))
            if(!(anno ==0))
                System.out.println("anno dato corretto");
            else{
                msg="anno non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("regione").matches(regString))
            if(!regione.equals(""))
                System.out.println("regione dato corretto");
            else{
                msg="regione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("gradazione").matches(regString))
            if(!(grad==0))
                System.out.println("gradazione dato corretto");
            else{
                msg="gradazione non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("formato").matches(regString))
            if(!(forma ==0))
                System.out.println("formato dato corretto");
            else{
                msg="formato non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("quantita_magazzino").matches(regString))
            if(!(qnt==0))
                System.out.println("quantita dato corretto");
            else{
                msg="quantita_magazzino non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if(!request.getParameter("nome_categoria").matches(regString))
            if(!nome_categoria.equals(""))
                System.out.println("nome_categoria dato corretto");
            else{
                msg="nome_categoria non corretto";
                System.out.println(msg);
                request.setAttribute("errorTest",msg);
                validate=false; }

        if (validate == true) {
            System.out.println("tutti i campi sono giusti");
        } else {
            RequestDispatcher view = request.getRequestDispatcher("ModificaProdottoByAdmin.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            currentSession.setAttribute("error", "error");
            view.forward(request, response);
            return;
        }

        String id = (String) request.getParameter("id");

        String nomecategoria = (String) request.getParameter("nome_categoria");

        Prodotto p = new Prodotto();
        ProdottoDAO proDao = new ProdottoDAO();

        p.setId(Integer.parseInt(id));
        p.setNome_categoria(nomecategoria);
        p.setNome(nome);
        p.setDescrizione(descrizione);
        p.setAnno(anno);
        p.setRegione(regione);
        p.setGradazione(Integer.parseInt(gradazione));
        p.setFormato(Integer.parseInt(formato));
        p.setQuantita_magazzino(qnt);
        p.setTipo(tipo);
        p.setPrezzo(Double.parseDouble(prezzo));
        p.setSconto(Double.parseDouble(sconto));
        p.setImmagine(immagine);
        proDao.doUpdate(p);

        msg="Prodotto modificato con successo";
        request.setAttribute("errorTest",msg);
        String address = "ProfiloAmministratore.jsp";
        response.sendRedirect(address);
    }

    public void gestoreModificaProdottoByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String param=request.getParameter("id");
        int id=Integer.parseInt(param);

        ProdottoDAO udao=new ProdottoDAO();
        Prodotto p=udao.retriveOne(id);
        request.setAttribute("prodotto1",p);
        String address="/ModificaProdottoByAdmin.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreMostraProdotto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Prende il valore della stringa passata e co prendiamo l'id del nostro prodotto
        String product=request.getParameter("product");
        String address = null;

        ProdottoDAO proDAO = new ProdottoDAO();

        Prodotto p=proDAO.retriveOne(Integer.parseInt(product));
        request.setAttribute("prodotto", p);

        address = "/Prodotto.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
        response.sendRedirect(address);
        if(product==null) {

            String id=request.getParameter("prodId");
            int prodId=Integer.parseInt(id);
            p=proDAO.retriveOne(prodId);
            request.setAttribute("prodotto", p);
            address = "/Prodotto.jsp";
            dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

    }

    public void gestoreMostraTuttiProdotti(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdottoDAO proDAO = new ProdottoDAO();
        String address = null;

        List<Prodotto> prodotti = proDAO.retriveAll();

        request.setAttribute("prodotti", prodotti);
        address = "/ListaProdotti.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public  void gestoreRicercaProdotto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String s = request.getParameter("stringP");
        ProdottoDAO proDAO = new ProdottoDAO();
        String address = null;

        List<Prodotto> prodotti = proDAO.retriveBySearch(s);

        request.setAttribute("prodotti", prodotti);
        address = "/RicercaProdotto.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
