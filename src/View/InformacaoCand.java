package View;

import Controller.CandidatoController;
import Model.Candidato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InformacaoCand  extends JFrame{

    private JLabel lbNumero, lbNome, lbPartido, lbVotos;
    private JTextField txtNumero, txtNome, txtPartido, txtLocalizar;
    private JButton  btnLocalizar, btnVoltar;



    public InformacaoCand(){
        super("Informação de Candidatos");

        Container tela = getContentPane();

        setLayout(null);
        lbNome = new JLabel("");
        lbNumero = new JLabel("Numero");
        lbPartido = new JLabel("");

        tela.add(lbNumero);
        tela.add(lbNome);
        tela.add(lbPartido);


        txtNome = new JTextField();
        txtPartido = new JTextField();
        txtLocalizar = new JTextField();
        txtNumero = new JTextField();


        lbNome.setBounds(10, 30, 265, 20);
        lbNumero.setBounds(10, 60, 265, 20);
        txtLocalizar.setBounds(10,90,265,20);
        lbPartido.setBounds(10,120,265,20);


        tela.add(txtNome);
        tela.add(txtPartido);
        tela.add(txtLocalizar);


        btnLocalizar = new JButton("Localizar");
        btnVoltar = new JButton("Voltar");

        btnLocalizar.setBounds(280, 25, 100, 20);
        btnVoltar.setBounds(280, 65, 80, 20);

        tela.add(btnLocalizar);
        tela.add(btnVoltar);

        setSize(400, 250);
        setLocationRelativeTo(null);


        btnLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                onclickLocalizar();

            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CandidatoFrame frame = new CandidatoFrame();

            }
        });

    }

    private void onclickLocalizar() {

        CandidatoController cc   = new CandidatoController();

        try {

            Candidato c = cc.buscaMotoristaPorNumero(txtLocalizar.getText());
            lbNome.setText(c.getNome());
            lbPartido.setText(c.getPartido());

        }catch (SQLException e){
            JOptionPane.showMessageDialog(this,
                    "Ocorreu um erro, tente novamente! " +
                            e.getLocalizedMessage());
        }
    }


}
