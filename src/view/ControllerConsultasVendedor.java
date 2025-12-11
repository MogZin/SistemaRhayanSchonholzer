package view;

import bean.RpsVendedor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasVendedor extends AbstractTableModel {

    private List lstVendedor;

    public void setList(List lstVendedor) {
        this.lstVendedor = lstVendedor;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstVendedor.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RpsVendedor rpsVendedor = (RpsVendedor) lstVendedor.get(rowIndex);
        if (columnIndex == 0) {
            return rpsVendedor.getRpsIdvendedor();
        } else if (columnIndex == 1) {
            return rpsVendedor.getRpsNome();
        } else if (columnIndex == 2) {
            return rpsVendedor.getRpsSalario();
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
            return "Salário";
        }
        return "";
    }
}
