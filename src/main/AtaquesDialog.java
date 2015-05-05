package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.glassfish.gmbal.ManagedAttribute;

public class AtaquesDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public Battle mainBattle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AtaquesDialog dialog = new AtaquesDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AtaquesDialog(final Ataque ataques[]) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 340, 450, 60);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(ataques[0].nome);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ATK(0));
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton(ataques[1].nome);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ATK(1));
				buttonPane.add(cancelButton);
			}
			{
				JButton cancelButton = new JButton(ataques[2].nome);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ATK(2));
				buttonPane.add(cancelButton);
			}
			{
				JButton cancelButton = new JButton(ataques[3].nome);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ATK(3));
				buttonPane.add(cancelButton);
			}
		}
	}
	class ATK implements ActionListener
	{
		Integer atkIndex;
		public ATK(Integer atkIndex)
		{
			this.atkIndex = atkIndex;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			confront(atkIndex);
		}
		
		private void confront(int playerIndex) {
			
			Ataque playerAtaque = mainBattle.jogador.getPokemonAtivo().ataques[playerIndex];
			Actions AIAction = mainBattle.AI.generateAction();
			if (AIAction == Actions.Atacar) {
				Ataque AIAtaque = mainBattle.AI.generateAtack();
				if (playerAtaque.prioridade >= AIAtaque.prioridade) {
					mainBattle.jogador.atacarWithPokemon(atkIndex, mainBattle.AI.getPokemonAtivo());
					int HealthPoint = (int)(((double)mainBattle.AI.getPokemonAtivo().HP/mainBattle.AI.getPokemonAtivo().HPMAX) * 100);
					mainBattle.AILife.setValue(HealthPoint);
					mainBattle.textPane.setText( mainBattle.textPane.getText() + "\nVocê usou " + mainBattle.jogador.getPokemonAtivo().ataques[atkIndex].nome);
					if (HealthPoint > 0) {
						mainBattle.AI.atacarWithPokemon(AIAtaque.ID, mainBattle.jogador.getPokemonAtivo());
						mainBattle.textPane.setText( mainBattle.textPane.getText() + "\n" + mainBattle.AI.getPokemonAtivo().nome + " usou " + AIAtaque.nome);
					}
					
				} else {
					
					mainBattle.AI.atacarWithPokemon(AIAtaque.ID, mainBattle.jogador.getPokemonAtivo());
					mainBattle.textPane.setText( mainBattle.textPane.getText() + "\n" + mainBattle.AI.getPokemonAtivo().nome + " usou " + AIAtaque.nome);
					
					if (mainBattle.jogador.getPokemonAtivo().HP > 0) {
						mainBattle.jogador.atacarWithPokemon(atkIndex, mainBattle.AI.getPokemonAtivo());
						int HealthPoint = (int)(((double)mainBattle.AI.getPokemonAtivo().HP/mainBattle.AI.getPokemonAtivo().HPMAX) * 100);
						mainBattle.AILife.setValue(HealthPoint);
						mainBattle.textPane.setText( mainBattle.textPane.getText() + "\nVocê usou " + mainBattle.jogador.getPokemonAtivo().ataques[atkIndex].nome);
					}
				}
			} else if (AIAction == Actions.Item) {
				
			}
		
			mainBattle.updateBattle();
			AtaquesDialog.this.dispose();
		}
		
	}
	
}
