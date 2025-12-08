package view;

import bean.RpsProdutos;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ControllerConsultasProdutos extends AbstractTableModel {

    private List lstProdutos;

    public void setList(List lstProdutos) {
        this.lstProdutos = lstProdutos;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lstProdutos.size();

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RpsProdutos rpsProdutos = (RpsProdutos) lstProdutos.get(rowIndex);
        if (columnIndex == 0) {
            return rpsProdutos.getRpsIdJogo();
        } else if (columnIndex == 1) {
            return rpsProdutos.getRpsNome();
        } else if (columnIndex == 2) {
            return rpsProdutos.getRpsValor();
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
            return "Valor Unitário";
        }
        return "";
    }
}
