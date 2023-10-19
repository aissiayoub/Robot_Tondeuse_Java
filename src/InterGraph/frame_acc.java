package InterGraph;

import javax.swing.*;

import InterGraph.Plateau_Princip.Plateau_Simulation;
import Simple.Plateau2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class frame_acc extends JFrame {

	private JPanel p;
	private JButton b;
	private JTextField t1, t2;
	private JComboBox bx;
	private String[] algos = { "Normal", "Spirale", "Random" };
	private String algo;
	private int Larg, Long;
	private Plateau_Princip Principal;

	public frame_acc() {

		Larg = 0;
		Long = 0;

		p = new JPanel();
		b = new JButton("submit");

		t1 = new JTextField(5);
		t2 = new JTextField(5);

		bx = new JComboBox(algos);

		b.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Principal = new  Plateau_Princip("ROBOT TONDEUSE SIMUMATION",Integer.parseInt(t1.getText())*60,Integer.parseInt(t2.getText())*60);
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Principal.pack();
				Principal.setVisible(true);
				try {
					Principal.algo_spirale();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}});

		p.add(t1);
		p.add(t2);
		p.add(bx);
		p.add(b);
		this.add(p);
	}

	public static void main(String[] arg0) throws InterruptedException {
		frame_acc fr = new frame_acc();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.pack();
		fr.setVisible(true);

	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public int getLarg() {
		return Larg;
	}

	public void setLarg(int larg) {
		Larg = larg;
	}

	public int getLong() {
		return Long;
	}

	public void setLong(int l) {
		Long = l;
	}

}
