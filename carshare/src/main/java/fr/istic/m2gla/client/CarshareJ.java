
package fr.istic.m2gla.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CarshareJ implements EntryPoint {


    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */

    private final Messages messages = GWT.create(Messages.class);

    private static final int HeaderRowIndex = 0;
    private static final Object[][] rowData = {
            {new RadioButton("RadioGrp1", "Personne"), new RadioButton("RadioGrp2", "Création")},
            {new RadioButton("RadioGrp1", "Voiture"), new RadioButton("RadioGrp2", "Suppression")},
            {new RadioButton("RadioGrp1", "Ville"), new RadioButton("RadioGrp2", "Afficher les tous")},
            {new RadioButton("RadioGrp1", "Avis"), new RadioButton("RadioGrp2", "Afficher un seul")},
            {new RadioButton("RadioGrp1", "Preference"), new RadioButton("RadioGrp2", "Modification")},
            {new RadioButton("RadioGrp1", "Evenement"), "HaHa"},
    };

    private FlexTable flexTable = new FlexTable();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        flexTable.insertRow(HeaderRowIndex);
        flexTable.getRowFormatter().addStyleName(HeaderRowIndex, "FlexTable-Header");

        addColumn("Objects");
        addColumn("Actions");


        for (int row = 0; row < rowData.length; row++) {
            addRow(rowData[row]);

        }

        applyDataRowStyles();

        flexTable.setCellSpacing(0);
        flexTable.addStyleName("FlexTable");

        RootPanel.get("container").add(flexTable);


        Button b = new Button("Go!", new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("How high?");
            }
        });

        // Add it to the root panel.
        RootPanel.get("container").add(b);


        if (true) {
            // Make a new list box, adding a few items to it.
            ListBox lb = new ListBox();
            lb.addItem("foo");
            lb.addItem("bar");
            lb.addItem("baz");
            lb.addItem("toto");
            lb.addItem("tintin");

            // Make enough room for all five items (setting this value to 1 turns it
            // into a drop-down list).
            lb.setVisibleItemCount(1);

            // Add it to the root panel.
            RootPanel.get("container").add(lb);

        }


        FlowPanel fp = new FlowPanel();

        fp.setStyleName("center");
        RootPanel.get().add(fp);


    }

    private void addColumn(Object columnHeading) {
        Widget widget = createCellWidget(columnHeading);
        int cell = flexTable.getCellCount(HeaderRowIndex);

        widget.setWidth("100%");
        widget.addStyleName("FlexTable-ColumnLabel");

        flexTable.setWidget(HeaderRowIndex, cell, widget);

        flexTable.getCellFormatter().addStyleName(
                HeaderRowIndex, cell, "FlexTable-ColumnLabelCell");
    }

    private Widget createCellWidget(Object cellObject) {
        Widget widget = null;

        if (cellObject instanceof Widget)
            widget = (Widget) cellObject;
        else
            widget = new Label(cellObject.toString());

        return widget;
    }

    int rowIndex = 1;

    private void addRow(Object[] cellObjects) {

        for (int cell = 0; cell < cellObjects.length; cell++) {
            Widget widget = createCellWidget(cellObjects[cell]);
            flexTable.setWidget(rowIndex, cell, widget);
            flexTable.getCellFormatter().addStyleName(rowIndex, cell, "FlexTable-Cell");
        }
        rowIndex++;
    }

    private void applyDataRowStyles() {
        HTMLTable.RowFormatter rf = flexTable.getRowFormatter();

        for (int row = 1; row < flexTable.getRowCount(); ++row) {
            if ((row % 2) != 0) {
                rf.addStyleName(row, "FlexTable-OddRow");
            } else {
                rf.addStyleName(row, "FlexTable-EvenRow");
            }
        }
    }


    private List<String> afficherList(String type) {
        List<String> entity = new ArrayList<String>();

        entity.add("Personne");
        entity.add("Voiture");
        entity.add("Ville");
        entity.add("Avis");
        entity.add("Preference");
        entity.add("Evenement");

        List<String> values = new ArrayList<String>();

        if (entity.contains(type)) {
            values.add("List des XXXX");
            //utilisation du travail de mamadou
        } else {
            values.add("Aucun");
        }
        return values;
    }
}

