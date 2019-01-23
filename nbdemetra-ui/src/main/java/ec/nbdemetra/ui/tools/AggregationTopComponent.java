/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.nbdemetra.ui.tools;

import demetra.bridge.TsConverter;
import demetra.tsprovider.TsCollection;
import demetra.ui.TsManager;
import demetra.ui.components.HasTsCollection.TsUpdateMode;
import demetra.ui.util.NbComponents;
import ec.tss.Ts;
import ec.tss.TsInformationType;
import ec.tss.TsStatus;
import ec.tstoolkit.timeseries.simplets.TsData;
import demetra.ui.components.JTsChart;
import demetra.ui.components.JTsTable;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//ec.nbdemetra.ui.tools//Aggregation//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "AggregationTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "ec.nbdemetra.ui.tools.AggregationTopComponent")
@ActionReference(path = "Menu/Tools", position = 332)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_AggregationAction")
@Messages({
    "CTL_AggregationAction=Aggregation",
    "CTL_AggregationTopComponent=Aggregation Window",
    "HINT_AggregationTopComponent=This is an Aggregation window"
})
public final class AggregationTopComponent extends TopComponent {

    private final JSplitPane mainPane;
    private final JTsTable inputList;
    private final JTsChart aggChart;

    public AggregationTopComponent() {
        initComponents();
        setName(Bundle.CTL_AggregationTopComponent());
        setToolTipText(Bundle.HINT_AggregationTopComponent());
        inputList = new JTsTable();
        initList();
        aggChart = new JTsChart();
        aggChart.setTsUpdateMode(TsUpdateMode.None);
        mainPane = NbComponents.newJSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputList, aggChart);

        setLayout(new BorderLayout());
        add(mainPane, BorderLayout.CENTER);
        mainPane.setDividerLocation(.5);
        mainPane.setResizeWeight(.5);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    private void initList() {
        inputList.addPropertyChangeListener(JTsTable.TS_COLLECTION_PROPERTY, evt -> {
            TsData sum = null;
            for (demetra.tsprovider.Ts o : inputList.getTsCollection().getData()) {
                Ts s = TsConverter.fromTs(o);
                if (s.hasData() == TsStatus.Undefined) {
                    TsManager.getDefault().load(s, TsInformationType.Data);
                }
                sum = TsData.add(sum, s.getTsData());
            }
            Ts t = TsManager.getDefault().newTs("Total", null, sum);
            aggChart.setTsCollection(TsCollection.of(TsConverter.toTs(t)));
        });
    }
}
