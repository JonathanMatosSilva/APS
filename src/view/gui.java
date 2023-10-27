package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import model.bean.Pet;
import model.dao.PetDAO;

import model.ordenacao.*;

public class gui {

	public JFrame frame;
	private JTextField tfNome;
	private JTextField tfCodigo;
	private JTextField tfRaca;
	private JTextField tfNomeDoTutor;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBox;
	private FileInputStream fis;
	private int tamanho;
	private JLabel lblImagem;
	private JLabel lblMergeSort;
	private JLabel lblBubbleSort;
	private JLabel lblQuickSort;
    PetDAO cdao = new PetDAO();
    List<Pet> Pets = cdao.read();

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 773, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelCodigo = new JLabel("ID");
		labelCodigo.setBounds(10, 11, 33, 14);
		frame.getContentPane().add(labelCodigo);

		JLabel lblNomeDoPet = new JLabel("Nome do Pet");
		lblNomeDoPet.setBounds(53, 11, 67, 14);
		frame.getContentPane().add(lblNomeDoPet);

		tfNome = new JTextField();
		tfNome.setToolTipText("");
		tfNome.setBounds(53, 32, 185, 20);
		frame.getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblPorte = new JLabel("Porte");
		lblPorte.setBounds(63, 63, 46, 14);
		frame.getContentPane().add(lblPorte);

		tfCodigo = new JTextField();
		tfCodigo.setBackground(Color.LIGHT_GRAY);
		tfCodigo.setEnabled(false);
		tfCodigo.setBounds(10, 32, 33, 20);
		frame.getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);

		JLabel lblraca = new JLabel("Raça");
		lblraca.setBounds(180, 63, 46, 14);
		frame.getContentPane().add(lblraca);

		tfRaca = new JTextField();
		tfRaca.setBounds(152, 88, 86, 20);
		frame.getContentPane().add(tfRaca);
		tfRaca.setColumns(10);

		JLabel lblNomeDoTutor = new JLabel("Nome do Tutor");
		lblNomeDoTutor.setBounds(247, 11, 82, 14);
		frame.getContentPane().add(lblNomeDoTutor);

		tfNomeDoTutor = new JTextField();
		tfNomeDoTutor.setBounds(247, 32, 185, 20);
		frame.getContentPane().add(tfNomeDoTutor);
		tfNomeDoTutor.setColumns(10);

		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setBounds(38, 153, 46, 14);
		frame.getContentPane().add(labelSexo);

		JRadioButton rbMacho = new JRadioButton("Macho");
		buttonGroup.add(rbMacho);
		rbMacho.setBounds(192, 149, 67, 23);
		frame.getContentPane().add(rbMacho);

		JRadioButton rbFemea = new JRadioButton("Fêmea");
		buttonGroup.add(rbFemea);
		rbFemea.setBounds(123, 149, 67, 23);
		frame.getContentPane().add(rbFemea);
		
		DefaultTableModel modelo = new DefaultTableModel(new String[] { "ID", "Nome do Pet", "Nome do Tutor", "Porte", "Raça", "Sexo"}, 0);
		table = new JTable(modelo);
		table.setBackground(Color.LIGHT_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) { // -1 representa que não selecionou a linha da tabela= indice -1
					tfCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					tfNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					tfNomeDoTutor.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					comboBox.setSelectedItem(table.getValueAt(table.getSelectedRow(), 3));
					tfRaca.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					if ("Macho".equals(table.getValueAt(table.getSelectedRow(), 5).toString())) {
					    rbMacho.setSelected(true);
					    rbFemea.setSelected(false);
					} else {
					    rbMacho.setSelected(false);
					    rbFemea.setSelected(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha para ser alterada.");
				}
				
				// Insere imagem na tela
				int linhaSelecionada = table.getSelectedRow();
                Image img = Pets.get(linhaSelecionada).getFoto();
                lblImagem.setIcon(new ImageIcon(img));
                Pets = cdao.read();
			}
		});
		JScrollPane scrollPane = new JScrollPane(table); // Adicione a tabela a um JScrollPane
		scrollPane.setBounds(10, 364, 732, 180);
		frame.getContentPane().add(scrollPane);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pet p = new Pet();
				PetDAO dao = new PetDAO();
				p.setNome(tfNome.getText());
				p.setTutor(tfNomeDoTutor.getText());
				p.setPorte(comboBox.getSelectedItem().toString());
				p.setRaca(tfRaca.getText());
				if (rbMacho.isSelected()) {
					p.setSexo("Macho");
				} else {
					p.setSexo("Fêmea");
				}

				Icon icon = lblImagem.getIcon();
				ImageIcon imageIcon = (ImageIcon) icon;
				Image imagem = imageIcon.getImage();
				p.setFoto(imagem);
				dao.create(p);
				readJTable();
			}
		});
		btnCadastrar.setBounds(10, 274, 100, 23);
		frame.getContentPane().add(btnCadastrar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Pet p = new Pet();
					PetDAO dao = new PetDAO();
					p.setCodigo((int) table.getValueAt(table.getSelectedRow(), 0));
					dao.delete(p);
					readJTable();
					tfNome.setText("");
					tfNomeDoTutor.setText("");
					tfRaca.setText("");
					tfCodigo.setText("");
					buttonGroup.clearSelection();
					comboBox.setSelectedItem(null);
					lblImagem.setIcon(null);
				}
			}
		});
		btnExcluir.setBounds(219, 274, 89, 23);
		frame.getContentPane().add(btnExcluir);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					Pet p = new Pet();
					PetDAO dao = new PetDAO();
					p.setCodigo((int) table.getValueAt(table.getSelectedRow(), 0));
					p.setNome(tfNome.getText());
					p.setTutor(tfNomeDoTutor.getText());
					p.setPorte(comboBox.getSelectedItem().toString());
					p.setRaca(tfRaca.getText());
					if (rbMacho.isSelected()) {
						p.setSexo("Macho");
					} else {
						p.setSexo("Femea");
					}
					Icon icon = lblImagem.getIcon();
					ImageIcon imageIcon = (ImageIcon) icon;
					Image imagem = imageIcon.getImage();
					p.setFoto(imagem);
					dao.update(p);
					readJTable();
				}
			}
		});
		btnAlterar.setBounds(120, 274, 89, 23);
		frame.getContentPane().add(btnAlterar);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Pequeno", "Medio", "Grande"}));
		comboBox.setBounds(34, 88, 86, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnImagem = new JButton("Selecionar Imagem");
		btnImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}
		});
		btnImagem.setBounds(38, 221, 220, 23);
		frame.getContentPane().add(btnImagem);
		
		lblImagem = new JLabel("");
		lblImagem.setBounds(442, 11, 300, 300);
		lblImagem.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(lblImagem);
		
		JButton btnLimparCampos = new JButton("Limpar");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNome.setText("");
				tfNomeDoTutor.setText("");
				tfRaca.setText("");
				tfCodigo.setText("");
				buttonGroup.clearSelection();
				comboBox.setSelectedItem(null);
				lblImagem.setIcon(null);
				lblQuickSort.setText("");
				lblMergeSort.setText("");
				lblBubbleSort.setText("");
				readJTable();
			}
		});
		btnLimparCampos.setBounds(318, 274, 89, 23);
		frame.getContentPane().add(btnLimparCampos);
		
		JButton btnBubbleSort = new JButton("BubbleSort");
		btnBubbleSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordenacao ord = new Ordenacao();
				long tmpInicial = System.currentTimeMillis();

				ord.bubbleSortJTable(table, 1);
		        long endTime = System.currentTimeMillis();
		        long elapsedTime = endTime - tmpInicial;
		        lblBubbleSort.setText(String.valueOf(elapsedTime) + "ms");
				
			}
		});
		btnBubbleSort.setBounds(123, 308, 86, 23);
		frame.getContentPane().add(btnBubbleSort);
		
		JButton btnQuickSort = new JButton("QuickSort");
		btnQuickSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordenacao ord = new Ordenacao();
				long tmpInicial = System.currentTimeMillis();

				ord.quickSortJTable(table, 1, 0, table.getRowCount() - 1);
		        long endTime = System.currentTimeMillis();
		        long elapsedTime = endTime - tmpInicial;
		        lblQuickSort.setText(String.valueOf(elapsedTime) + "ms");
			}
		});
		btnQuickSort.setBounds(10, 308, 99, 23);
		frame.getContentPane().add(btnQuickSort);
		
		JButton btnMergeSort = new JButton("Merge Sort");
		btnMergeSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ordenacao ord = new Ordenacao();
				long tmpInicial = System.currentTimeMillis();

				ord.mergeSortJTable(table, 1);
		        long endTime = System.currentTimeMillis();
		        long elapsedTime = endTime - tmpInicial;
		        lblMergeSort.setText(String.valueOf(elapsedTime) + "ms");
			}
		});
		btnMergeSort.setBounds(219, 308, 89, 23);
		frame.getContentPane().add(btnMergeSort);
		
		lblQuickSort = new JLabel("");
		lblQuickSort.setBounds(38, 339, 46, 14);
		frame.getContentPane().add(lblQuickSort);
		
		lblBubbleSort = new JLabel("");
		lblBubbleSort.setBounds(144, 339, 46, 14);
		frame.getContentPane().add(lblBubbleSort);
		
		lblMergeSort = new JLabel("");
		lblMergeSort.setBounds(240, 339, 46, 14);
		frame.getContentPane().add(lblMergeSort);
	}
	
	private void carregarFoto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar Arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens (*.PNG, *.JPG, *.JPEG)", "png", "jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(frame);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);
				lblImagem.setIcon(new ImageIcon(foto));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void readJTable() {
	    DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	    modelo.setNumRows(0);
	    Pets = cdao.read();
	    for (Pet p : Pets) {
	        modelo.addRow(new Object[] {
	            p.getCodigo(),
	            p.getNome(),
	            p.getTutor(),
	            p.getPorte(),
	            p.getRaca(),
	            p.getSexo(),
	        });
	    }
	}
}
