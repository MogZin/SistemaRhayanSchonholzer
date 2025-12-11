package view;

import bean.RpsClientes;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasClientes extends AbstractTableModel {

    private List lstClientes;

    public void setList(List lstClientes) {
        this.lstClientes = lstClientes;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstClientes.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RpsClientes rpsClientes = (RpsClientes) lstClientes.get(rowIndex);
        if (columnIndex == 0) {
            return rpsClientes.getRpsIdclientes();
        } else if (columnIndex == 1) {
            return rpsClientes.getRpsNome();
        } else if (columnIndex == 2) {
            return rpsClientes.getRpsGenero();
        }
        return "";
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Código";
        } else if (columnIndex == 1) {
            return "Nome";
        } else if (columnIndex == 2) {
            return "Gênero";
        }
        return "";
    }
}
