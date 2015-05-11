package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AtaquesDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public Battle mainBattle;

	/**
	 * Create the dialog.
	 */
	public AtaquesDialog(final Ataque ataques[], Battle battle) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.mainBattle = battle;
		setBounds(100, 340, 500, 60);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			for (int i = 0; i < mainBattle.jogador.getPokemonAtivo().numAtaques ; i++) {
				{
					JButton atkButton = new JButton(mainBattle.jogador.getPokemonAtivo().ataques[i].nome);
					atkButton.addActionListener(new ATK(i));
					buttonPane.add(atkButton);
				}
			}
			
//			{
//				JButton cancelButton = new JButton(ataques[1].nome);
//				cancelButton.setActionCommand("Cancel");
//				cancelButton.addActionListener(new ATK(1));
//				buttonPane.add(cancelButton);
//			}
//			{
//				JButton cancelButton = new JButton(ataques[2].nome);
//				cancelButton.setActionCommand("Cancel");
//				cancelButton.addActionListener(new ATK(2));
//				buttonPane.add(cancelButton);
//			}
//			{
//				JButton cancelButton = new JButton(ataques[3].nome);
//				cancelButton.setActionCommand("Cancel");
//				cancelButton.addActionListener(new ATK(3));
//				buttonPane.add(cancelButton);
//			}
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
