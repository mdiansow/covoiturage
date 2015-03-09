package fr.istic.m2gla.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.google.gwt.widget.client.TextButton;
import fr.istic.m2gla.shared.IEvent;
import fr.istic.m2gla.shared.IPerson;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Carshare implements EntryPoint, ClickHandler {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    // The list of data to display.
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");


    private FlexTable loginFlexTable;
    private TextButton txtbtnLogin;
    private TextBox txtbxUsername;
    private PasswordTextBox pswrdtxtbxPassword;
    private TextBox txtbxNom;
    private TextBox txtbxPrnom;
    private TextBox txtbxPseudo;
    private TextBox txtbxEmail;
    private PasswordTextBox pswrdtxtbxPassword_add;
    private PasswordTextBox pswrdtxtbxConfirmePassword;
    private DateBox dateDateBox;
    private TextBox txtbxCommuneDepart;
    private TextBox txtbxCommuneArriveer;
    private TextBox txtbxCodePostalArrivee;
    private FlexTable addEventFlexTable;
    private FlexTable addVoitureFlexTable;
    private FlexTable addUserFlexTable;
    private ListBox listBoxMarque;
    private TextButton txtbtnEnregistrerAnnonce;
    private HorizontalPanel horizontalPanelAdd;
    private CellTable<IEvent> tableEvent;
    private TextButton txtbtnRejoindre;
    private Label errorLabel;
    private Label lblConfirmePassword;
    private DateBox dateBoxDateDeNaissance;
    private TextButton txtbtnEnregistrerUtilisateur;
    private ListBox listBox;


    /* My autobean factory */
    private EntityJsonConverter.EntityFactory factory = GWT.create(EntityJsonConverter.EntityFactory.class);

    private static List<IEvent> EVENTS = new ArrayList<>();


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        RootPanel rootPanel = RootPanel.get("nameFieldContainer");
        rootPanel.setWidth("100%");
        errorLabel = new Label();
        rootPanel.add(errorLabel);

        VerticalPanel verticalPanel = new VerticalPanel();
        rootPanel.add(verticalPanel);
        verticalPanel.setWidth("100%");
        verticalPanel.setSpacing(10);

        horizontalPanelAdd = new HorizontalPanel();

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        verticalPanel.add(horizontalPanel);
        horizontalPanel.setSize("100%", "");

        loginFlexTable = new FlexTable();
        horizontalPanel.add(loginFlexTable);
        loginFlexTable.setCellPadding(5);
        loginFlexTable.setSize("60%", "");

        txtbxUsername = new TextBox();
        txtbxUsername.setText("Username");
        loginFlexTable.setWidget(0, 1, txtbxUsername);
        txtbxUsername.setWidth("90%");

        Label usernameLabel = new Label("Username");
        loginFlexTable.setWidget(1, 0, usernameLabel);

        Label lblPasswordlabel = new Label("Password");
        loginFlexTable.setWidget(0, 0, lblPasswordlabel);

        pswrdtxtbxPassword = new PasswordTextBox();
        pswrdtxtbxPassword.setText("Password");
        loginFlexTable.setWidget(1, 1, pswrdtxtbxPassword);
        pswrdtxtbxPassword.setWidth("90%");

        txtbtnLogin = new TextButton("Login");
        txtbtnLogin.addClickHandler(this);
        loginFlexTable.setWidget(2, 1, txtbtnLogin);
        loginFlexTable.getCellFormatter().setHorizontalAlignment(2, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        loginFlexTable.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        loginFlexTable.getCellFormatter().setHorizontalAlignment(0, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);

        txtbtnRejoindre = new TextButton("Rejoindre");
        horizontalPanel.add(txtbtnRejoindre);

        ScrollPanel scrollPanel = new ScrollPanel();

        // done
        // Create a CellTable
        tableEvent = new CellTable<>();

        //Create date column
        TextColumn<IEvent> dateColumn = new TextColumn<IEvent>() {
            @Override
            public String getValue(IEvent event) {
                return String.valueOf(event.getDate());
            }
        };
        // Create prenom column
        TextColumn<IEvent> priceColumn = new TextColumn<IEvent>() {
            @Override
            public String getValue(IEvent event) {
                return String.valueOf(event.getPrix());
            }
        };

        tableEvent.addColumn(dateColumn, "Date");
        tableEvent.addColumn(priceColumn, "Prix");

        // Set the total row count
        tableEvent.setRowCount(EVENTS.size());

        // Push the data into the widget
        tableEvent.setRowData(0, EVENTS);

        scrollPanel.setWidget(this.tableEvent);

        // Affichage de l'ensemble des personnes
        allPersonsRequest();


        horizontalPanel.add(scrollPanel);
        scrollPanel.setSize("50", "");

        horizontalPanelAdd.setSpacing(5);
        verticalPanel.add(horizontalPanelAdd);
        horizontalPanelAdd.setWidth("100%");

        addUserFlexTable = new FlexTable();
        horizontalPanelAdd.add(addUserFlexTable);
        addUserFlexTable.setWidth("100%");
        addUserFlexTable.setCellPadding(5);

        Label lblPasEncoreMembre = new Label(
                "Pas encore membre? Inscrivez-vous.");
        addUserFlexTable.setWidget(0, 0, lblPasEncoreMembre);

        Label lblCivilit = new Label("Civilit\u00E9");
        addUserFlexTable.setWidget(1, 0, lblCivilit);

        listBox = new ListBox();
        listBox.addItem("Monsieur", "Mr");
        listBox.addItem("Madame", "Mme");
        listBox.addItem("Mademoiselle", "Mlle");
        addUserFlexTable.setWidget(1, 1, listBox);
        addUserFlexTable.getCellFormatter().setWidth(1, 1, "60%");
        listBox.setSize("95%", "26px");
        listBox.setVisibleItemCount(1);

        Label lblNom = new Label("Nom");
        addUserFlexTable.setWidget(2, 0, lblNom);

        txtbxNom = new TextBox();
        txtbxNom.setText("Nom");
        addUserFlexTable.setWidget(2, 1, txtbxNom);
        addUserFlexTable.getCellFormatter().setWidth(2, 1, "40%");
        txtbxNom.setWidth("90%");

        Label lblPrnom = new Label("Pr\u00E9nom");
        addUserFlexTable.setWidget(3, 0, lblPrnom);

        txtbxPrnom = new TextBox();
        txtbxPrnom.setText("Pr\u00E9nom");
        addUserFlexTable.setWidget(3, 1, txtbxPrnom);
        addUserFlexTable.getCellFormatter().setWidth(3, 1, "90%");
        txtbxPrnom.setWidth("90%");

        Label lblPseudo = new Label("Pseudo");
        addUserFlexTable.setWidget(4, 0, lblPseudo);

        txtbxPseudo = new TextBox();
        txtbxPseudo.setText("Pseudo");
        addUserFlexTable.setWidget(4, 1, txtbxPseudo);
        addUserFlexTable.getCellFormatter().setWidth(4, 1, "40%");
        txtbxPseudo.setWidth("90%");

        Label lblEmail = new Label("Email");
        addUserFlexTable.setWidget(5, 0, lblEmail);

        txtbxEmail = new TextBox();
        txtbxEmail.setText("Email");
        addUserFlexTable.setWidget(5, 1, txtbxEmail);
        addUserFlexTable.getCellFormatter().setWidth(5, 1, "90%");
        txtbxEmail.setWidth("90%");

        Label lblPassword = new Label("Password");
        addUserFlexTable.setWidget(6, 0, lblPassword);

        pswrdtxtbxPassword_add = new PasswordTextBox();
        pswrdtxtbxPassword_add.setText("Password");
        addUserFlexTable.setWidget(6, 1, pswrdtxtbxPassword_add);
        addUserFlexTable.getCellFormatter().setWidth(6, 1, "60%");
        pswrdtxtbxPassword_add.setWidth("90%");

        lblConfirmePassword = new Label("Confirm Password");
        addUserFlexTable.setWidget(7, 0, lblConfirmePassword);
        addUserFlexTable.getCellFormatter().setWidth(7, 0, "50%");

        pswrdtxtbxConfirmePassword = new PasswordTextBox();
        pswrdtxtbxConfirmePassword.setText("Confirme password");
        addUserFlexTable.setWidget(7, 1, pswrdtxtbxConfirmePassword);
        addUserFlexTable.getCellFormatter().setWidth(7, 1, "60%");
        pswrdtxtbxConfirmePassword.setWidth("90%");
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(2, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(3, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(4, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(5, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(6, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(7, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);

        Label lblDateDeNaissance = new Label("Date de naissance");
        addUserFlexTable.setWidget(8, 0, lblDateDeNaissance);

        dateBoxDateDeNaissance = new DateBox();
        dateBoxDateDeNaissance.setFormat(new DefaultFormat(DateTimeFormat
                .getFormat("EE dd-MM-yyyy")));
        addUserFlexTable.setWidget(8, 1, dateBoxDateDeNaissance);
        addUserFlexTable.getCellFormatter().setWidth(8, 1, "90%");
        dateBoxDateDeNaissance.setWidth("90%");
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(8, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addUserFlexTable.getFlexCellFormatter().setColSpan(0, 0, 2);

        txtbtnEnregistrerUtilisateur = new TextButton(
                "Enregistrer utilisateur");
        txtbtnEnregistrerUtilisateur.addClickHandler(this);
        addUserFlexTable.setWidget(9, 0, txtbtnEnregistrerUtilisateur);
        addUserFlexTable.getFlexCellFormatter().setColSpan(9, 0, 2);
        addUserFlexTable.getCellFormatter().setHorizontalAlignment(9, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);

        addVoitureFlexTable = new FlexTable();
        addVoitureFlexTable.setVisible(false);
        horizontalPanelAdd.add(addVoitureFlexTable);
        addVoitureFlexTable.setCellPadding(5);
        addVoitureFlexTable.setWidth("240px");

        Label lblVotreVhicule = new Label("Votre v\u00E9hicule");
        addVoitureFlexTable.setWidget(0, 0, lblVotreVhicule);
        lblVotreVhicule.setWidth("100%");

        Label lblMarque = new Label("Marque");
        addVoitureFlexTable.setWidget(1, 0, lblMarque);

        listBoxMarque = new ListBox();
        addVoitureFlexTable.setWidget(1, 1, listBoxMarque);
        addVoitureFlexTable.getCellFormatter().setWidth(1, 1, "50%");
        listBoxMarque.setWidth("90%");
        listBoxMarque.setVisibleItemCount(1);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(1, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addVoitureFlexTable.getFlexCellFormatter().setColSpan(0, 0, 2);

        Label lblModle = new Label("Mod\u00E8le");
        addVoitureFlexTable.setWidget(2, 0, lblModle);

        ListBox listBox_1 = new ListBox();
        listBox_1.setVisibleItemCount(1);
        addVoitureFlexTable.setWidget(2, 1, listBox_1);
        listBox_1.setWidth("90%");

        Label lblConfort = new Label("Confort");
        addVoitureFlexTable.setWidget(3, 0, lblConfort);

        ListBox listBox_2 = new ListBox();
        listBox_2.setVisibleItemCount(1);
        addVoitureFlexTable.setWidget(3, 1, listBox_2);
        listBox_2.setWidth("90%");

        Label lblNombreDePlaces = new Label("Nombre de places");
        addVoitureFlexTable.setWidget(4, 0, lblNombreDePlaces);

        ListBox listBox_3 = new ListBox();
        listBox_3.setVisibleItemCount(1);
        addVoitureFlexTable.setWidget(4, 1, listBox_3);
        listBox_3.setWidth("90%");

        Label lblCatgorie = new Label("Cat\u00E9gorie");
        addVoitureFlexTable.setWidget(5, 0, lblCatgorie);

        ListBox listBox_4 = new ListBox();
        listBox_4.setVisibleItemCount(1);
        addVoitureFlexTable.setWidget(5, 1, listBox_4);
        listBox_4.setWidth("90%");

        Label lblCouleur = new Label("Couleur");
        addVoitureFlexTable.setWidget(6, 0, lblCouleur);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(2, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(3, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(4, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(5, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);

        ListBox listBox_5 = new ListBox();
        listBox_5.setVisibleItemCount(1);
        addVoitureFlexTable.setWidget(6, 1, listBox_5);
        listBox_5.setWidth("90%");
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(6, 1,
                HasHorizontalAlignment.ALIGN_RIGHT);

        TextButton txtbtnEnregistrerVhicule = new TextButton(
                "Enregistrer v\u00E9hicule");
        addVoitureFlexTable.setWidget(7, 0, txtbtnEnregistrerVhicule);
        addVoitureFlexTable.getFlexCellFormatter().setColSpan(7, 0, 2);
        addVoitureFlexTable.getCellFormatter().setHorizontalAlignment(7, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);

        addEventFlexTable = new FlexTable();
        addEventFlexTable.setVisible(false);
        horizontalPanelAdd.add(addEventFlexTable);
        addEventFlexTable.setCellPadding(5);
        addEventFlexTable.setWidth("100%");

        Label lblPublierUneAnnonce = new Label("Publier une annonce");
        addEventFlexTable.setWidget(0, 0, lblPublierUneAnnonce);

        Label lblDate = new Label("Date");
        addEventFlexTable.setWidget(1, 0, lblDate);

        dateDateBox = new DateBox();
        dateDateBox.setFormat(new DefaultFormat(DateTimeFormat
                .getFormat("EE dd-MM-yyyy")));
        addEventFlexTable.setWidget(1, 1, dateDateBox);
        dateDateBox.setWidth("100%");

        Label lblVilleDeDpart = new Label("Ville de d\u00E9part");
        addEventFlexTable.setWidget(2, 0, lblVilleDeDpart);
        lblVilleDeDpart.setWidth("100%");

        TextBox txtbxCodePostalDepart = new TextBox();
        txtbxCodePostalDepart.setText("Code postal");
        addEventFlexTable.setWidget(3, 0, txtbxCodePostalDepart);
        txtbxCodePostalDepart.setWidth("100%");

        txtbxCommuneDepart = new TextBox();
        txtbxCommuneDepart.setText("Commune");
        addEventFlexTable.setWidget(3, 1, txtbxCommuneDepart);
        txtbxCommuneDepart.setWidth("100%");

        Label lblVilleDarrive = new Label("Ville d'arriv\u00E9e");
        addEventFlexTable.setWidget(4, 0, lblVilleDarrive);
        lblVilleDarrive.setWidth("100%");

        txtbxCodePostalArrivee = new TextBox();
        txtbxCodePostalArrivee.setText("Code postal");
        addEventFlexTable.setWidget(5, 0, txtbxCodePostalArrivee);
        addEventFlexTable.getCellFormatter().setWidth(5, 0, "");
        txtbxCodePostalArrivee.setWidth("100%");

        txtbxCommuneArriveer = new TextBox();
        txtbxCommuneArriveer.setText("Commune");
        addEventFlexTable.setWidget(5, 1, txtbxCommuneArriveer);
        txtbxCommuneArriveer.setWidth("100%");
        addEventFlexTable.getFlexCellFormatter().setColSpan(2, 0, 2);
        addEventFlexTable.getFlexCellFormatter().setColSpan(4, 0, 2);
        addEventFlexTable.getFlexCellFormatter().setColSpan(0, 0, 2);

        txtbtnEnregistrerAnnonce = new TextButton("Enregistrer annonce");
        addEventFlexTable.setWidget(6, 0, txtbtnEnregistrerAnnonce);
        addEventFlexTable.getFlexCellFormatter().setColSpan(6, 0, 2);
        addEventFlexTable.getCellFormatter().setHorizontalAlignment(6, 0,
                HasHorizontalAlignment.ALIGN_RIGHT);

    }

    /**
     * Récupération des personnes
     */
    private void allPersonsRequest() {
        RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT
                .getHostPageBaseURL() + "rest/event/");
        rb.setCallback(new RequestCallback() {

            public void onError(Request request, Throwable exception) {
                Window.alert(exception.getMessage());
            }

            public void onResponseReceived(Request request,
                                           Response response) {
                String resp = response.getText();

                JSONValue jsonValue = JSONParser.parseStrict(resp);

                JSONArray array;
                EVENTS = new ArrayList<IEvent>();
                if ((array = jsonValue.isArray()) != null) {
                    for (int i = 0; i < array.size(); i++) {
                        String event = String.valueOf(array.get(i));
                        IEvent e = EntityJsonConverter.getInstance().deserializeEventFromJson(event);
                        EVENTS.add(e);
                        System.out.print("Person \t " + e.getDate());
                    }
                }
                tableEvent.setRowCount(EVENTS.size());
                tableEvent.setRowData(0, EVENTS);
                Window.alert("Event :\t" + EVENTS.get(0) + "\tSize \t" + EVENTS.size());
            }

        });
        try {
            rb.send();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(ClickEvent clickEvent) {
        if (clickEvent.getSource() == txtbtnLogin) {
            StringBuffer loginData = new StringBuffer();
            loginData.append(URL.encode("username")).append("=").append(URL.encode(txtbxUsername.getValue()));
            loginData.append(URL.encode("&password")).append("=").append(URL.encode(/*passwordTextBox.getValue()*/"password"));

            RequestBuilder loginRequestBuilder = new RequestBuilder(RequestBuilder.POST, GWT.getHostPageBaseURL() + "rest/user/login");
            loginRequestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");
            try {
                loginRequestBuilder.sendRequest(loginData.toString(), new RequestCallback() {
                    @Override
                    public void onResponseReceived(Request request, Response response) {
                        String username = response.getHeader("username");
//                        IPerson user = EntityJsonConverter.getInstance().deserializeUserFromJson(response.getText());
                        System.out.println("user\t" + username);
                        Window.alert("Bonjour " + username);
                        Cookies.setCookie("username", username);
                        addVoitureFlexTable.setVisible(true);
                        addEventFlexTable.setVisible(true);
                        loginFlexTable.setVisible(false);
                        addUserFlexTable.setVisible(false);
                    }

                    @Override
                    public void onError(Request request, Throwable throwable) {
                        Window.alert(throwable.getMessage());
                    }
                });
            } catch (RequestException e) {
                e.printStackTrace();
            }
        }
        if (clickEvent.getSource() == txtbtnEnregistrerUtilisateur) {
            IPerson user = EntityJsonConverter.getInstance().makeUser();
            String errors = "Remplir les champs ci-dessous\n";
            String nom = txtbxNom.getValue();
            String prenom = txtbxPrnom.getValue();
            String email = txtbxEmail.getValue();
            final String username = txtbxPseudo.getValue();
            String password = pswrdtxtbxPassword_add.getValue();
            String confirmPassword = pswrdtxtbxConfirmePassword.getValue();
            Date dateNaissance = dateBoxDateDeNaissance.getValue();
            String civilite = listBox.getValue(listBox.getSelectedIndex());
            if (nom != null) {
                user.setNom(nom);
            } else {
                errors += "Le nom est obligatoire\n";
            }
            if (prenom != null) {
                user.setPrenom(prenom);
            } else {
                errors += "Le prénom est obligatoire\n";
            }
            if (email != null) {
                user.setEmail(email);
            } else {
                errors += "L'email est obligatoire\n";
            }
            if (username != null) {
                user.setUsername(username);
            } else {
                errors += "Le username est obligatoire\n";
            }
            if (password != null && password.equals(confirmPassword)) {
                user.setPassword(password);
            } else {
                errors += "Les mots de pass sont différents\n";
            }
            if (dateNaissance != null) {
                user.setDateDeNaissance(dateNaissance);
            }
            if (civilite != null) {
                user.setCivilite(civilite);
            }
            String userJson = EntityJsonConverter.getInstance().serializeUserToJson(user);

            System.out.println("User Client  " + user.toString());
            if (!errors.equals("Remplir les champs ci-dessous\n")) {
                Window.alert(errors);
            } else {

                Window.alert("User -----  " + user.toString() + "\n" + userJson);
                RequestBuilder userRequestBuilder = new RequestBuilder(RequestBuilder.POST, GWT.getHostPageBaseURL() + "rest/user/create");
                userRequestBuilder.setHeader("Content-type", MediaType.APPLICATION_JSON);
                System.out.println("USER +++++++++++ " + userJson);
                errorLabel.setText(userJson);
                try {
                    userRequestBuilder.setRequestData(userJson);
                    userRequestBuilder.sendRequest(userJson, new RequestCallback() {
                        @Override
                        public void onResponseReceived(Request request, Response response) {
                            String message = response.getText();
                            Window.alert("L'utilisateur " + username + " a bien été ajouté.\n" + message);
                            if (message.equals("SUCCESS")) ;
                            {
                                Cookies.setCookie("username", username);
                                addVoitureFlexTable.setVisible(true);
                                loginFlexTable.setVisible(false);
                                addUserFlexTable.setVisible(false);
                                addEventFlexTable.setVisible(true);
                                allPersonsRequest();
                            }
                        }

                        @Override
                        public void onError(Request request, Throwable throwable) {
                            Window.alert(throwable.getMessage());
                        }
                    });
                } catch (RequestException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
