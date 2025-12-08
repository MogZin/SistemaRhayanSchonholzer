package view;

import bean.RpsUsuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasUsuarios extends AbstractTableModel {

    private List lstUsuarios;

    public void setList(List lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstUsuarios.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RpsUsuarios rpsUsuarios = (RpsUsuarios) lstUsuarios.get(rowIndex);
        if (columnIndex == 0) {
            return rpsUsuarios.getRpsIdusuarios();
        } else if (columnIndex == 1) {
            return rpsUsuarios.getRpsNome();
        } else if (columnIndex == 2) {
            return rpsUsuarios.getRpsApelido();
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
            return "Apelido";
        }
        return "";
    }
}
