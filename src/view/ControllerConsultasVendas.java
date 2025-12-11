package view;

import bean.RpsVendas;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasVendas extends AbstractTableModel {

    private List lstVendas;

    public void setList(List lstVendas) {
        this.lstVendas = lstVendas;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstVendas.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RpsVendas rpsVendas = (RpsVendas) lstVendas.get(rowIndex);
        if (columnIndex == 0) {
            return rpsVendas.getRpsIdVendas();
        } else if (columnIndex == 1) {
            return rpsVendas.getRpsClientes();
        } else if (columnIndex == 2) {
            return rpsVendas.getRpsVendedor();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Código";
        } else if (columnIndex == 1) {
            return "Cliente";
        } else if (columnIndex == 2) {
            return "Vendedor";
        }
        return "";
    }
}
