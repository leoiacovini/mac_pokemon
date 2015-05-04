package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ItemsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	public Treinador jogador;
	public Battle mainBattle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemsDialog dialog = new ItemsDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ItemsDialog(final Treinador jogador, Battle battle) {
		this.jogador = jogador;
		this.mainBattle = battle;
		setBounds(200, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Item itens[] = jogador.itens;
		
		for (Item item : itens) {
			JButton butao = new JButton(item.nome);
			
			butao.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					jogador.getPokemonAtivo().HP += 20;
					if (jogador.getPokemonAtivo().HP > jogador.getPokemonAtivo().HPMAX) {
						jogador.getPokemonAtivo().HP = jogador.getPokemonAtivo().HPMAX;
					}
					mainBattle.updateBattle();
					ItemsDialog.this.dispose();
				}
			});
			
			contentPanel.add(butao);
		}
		
	}

}


