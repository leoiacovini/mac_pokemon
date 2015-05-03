package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ItemsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public Treinador jogador;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemsDialog dialog = new ItemsDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ItemsDialog(Treinador jogador) {
		this.jogador = jogador;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Item itens[] = jogador.itens;
		
		for (Item item : itens) {
			JButton butao = new JButton(item.nome);
			contentPanel.add(butao);
		}
		
	}

}


