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
			mainBattle.jogador.atacarWithPokemon(atkIndex, mainBattle.AI.getPokemonAtivo());
			int HealthPoint = (int)(((double)mainBattle.AI.getPokemonAtivo().HP/mainBattle.AI.getPokemonAtivo().HPMAX) * 100);
			mainBattle.AILife.setValue(HealthPoint);
			AtaquesDialog.this.setVisible(false);
		}
	}
	
}
