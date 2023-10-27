package model.ordenacao;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ordenacao {


    public static void bubbleSortJTable(JTable table, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                Comparable<Object> value1 = (Comparable<Object>) model.getValueAt(j, columnIndex);
                Comparable<Object> value2 = (Comparable<Object>) model.getValueAt(j + 1, columnIndex);

                if (value1.compareTo(value2) > 0) {
                    // Troca as linhas
                    Object[] temp = new Object[model.getColumnCount()];
                    for (int k = 0; k < model.getColumnCount(); k++) {
                        temp[k] = model.getValueAt(j, k);
                        model.setValueAt(model.getValueAt(j + 1, k), j, k);
                        model.setValueAt(temp[k], j + 1, k);
                    }
                }
            }
        }
    }

    public static void quickSortJTable(JTable table, int columnIndex, int low, int high) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        if (low < high) {
            int pivotIndex = partition(table, columnIndex, low, high);
            quickSortJTable(table, columnIndex, low, pivotIndex - 1);
            quickSortJTable(table, columnIndex, pivotIndex + 1, high);
        }
    }

    private static int partition(JTable table, int columnIndex, int low, int high) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Comparable<Object> pivot = (Comparable<Object>) model.getValueAt(high, columnIndex);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            Comparable<Object> current = (Comparable<Object>) model.getValueAt(j, columnIndex);

            if (current.compareTo(pivot) < 0) {
                i++;
                swapRows(model, i, j);
            }
        }

        swapRows(model, i + 1, high);
        return i + 1;
    }

    private static void swapRows(DefaultTableModel model, int i, int j) {
        Object[] temp = new Object[model.getColumnCount()];
        for (int k = 0; k < model.getColumnCount(); k++) {
            temp[k] = model.getValueAt(i, k);
            model.setValueAt(model.getValueAt(j, k), i, k);
            model.setValueAt(temp[k], j, k);
        }
    }

	
    public static void mergeSortJTable(JTable table, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();

        if (rowCount <= 1) {
            return; // A tabela já está ordenada
        }

        int mid = rowCount / 2;
        Object[][] left = new Object[mid][model.getColumnCount()];
        Object[][] right = new Object[rowCount - mid][model.getColumnCount()];

        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                left[i][j] = model.getValueAt(i, j);
            }
        }

        for (int i = mid; i < rowCount; i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                right[i - mid][j] = model.getValueAt(i, j);
            }
        }

        DefaultTableModel leftModel = new DefaultTableModel(left, getColumnNames(table));
        DefaultTableModel rightModel = new DefaultTableModel(right, getColumnNames(table));

        mergeSortJTable(new JTable(leftModel), columnIndex);
        mergeSortJTable(new JTable(rightModel), columnIndex);

        merge(table, leftModel, rightModel, columnIndex);
    }

    private static void merge(JTable table, DefaultTableModel leftModel, DefaultTableModel rightModel, int columnIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int leftRowCount = leftModel.getRowCount();
        int rightRowCount = rightModel.getRowCount();
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < leftRowCount && rightIndex < rightRowCount) {
            Comparable<Object> leftValue = (Comparable<Object>) leftModel.getValueAt(leftIndex, columnIndex);
            Comparable<Object> rightValue = (Comparable<Object>) rightModel.getValueAt(rightIndex, columnIndex);

            if (leftValue.compareTo(rightValue) <= 0) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    model.setValueAt(leftModel.getValueAt(leftIndex, j), mergedIndex, j);
                }
                leftIndex++;
            } else {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    model.setValueAt(rightModel.getValueAt(rightIndex, j), mergedIndex, j);
                }
                rightIndex++;
            }

            mergedIndex++;
        }

        while (leftIndex < leftRowCount) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                model.setValueAt(leftModel.getValueAt(leftIndex, j), mergedIndex, j);
            }
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightRowCount) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                model.setValueAt(rightModel.getValueAt(rightIndex, j), mergedIndex, j);
            }
            rightIndex++;
            mergedIndex++;
        }
    }

    private static String[] getColumnNames(JTable table) {
        int columnCount = table.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = table.getColumnName(i);
        }
        return columnNames;
    }
}
