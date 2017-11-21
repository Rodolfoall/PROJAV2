package View;

import Controller.CandidatoController;
import Model.Candidato;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CandidatoFrame extends JFrame {



        private JLabel lbNumero, lbNome, lbPartido;
        private JTextField txtNumero, txtNome, txtPartido;
        private JButton btnSalvar, btnLocalizar;

        JProgressBar bar = new JProgressBar();
        Thread roda;



    private List candidatoList = new CandidatoController().listarCandidatos();




    public CandidatoFrame() {
        super("Cadastro de Candidatos");

        Container tela = getContentPane();

        setLayout(null);
        lbNome = new JLabel("Nome");
        lbNumero = new JLabel("Número");
        lbPartido = new JLabel("Partido");

        lbNome.setBounds(10,5,265,20);
        lbNumero.setBounds(10,43,265,20);
        lbPartido.setBounds(10,76,265,20);
        bar.setBounds(10,150,265,20);

        bar.setMinimum(0);
        bar.setMaximum(100);

        bar.setStringPainted(true);

        tela.add(bar);
        tela.add(lbNumero);
        tela.add(lbNome);
        tela.add(lbPartido);

        txtNumero = new JTextField();
        txtNome = new JTextField();
        txtPartido = new JTextField();

        txtNumero.setBounds(10, 25, 265, 20);
        txtNome.setBounds(10, 60, 265, 20);
        txtPartido.setBounds(10, 94, 265, 20);

        tela.add(txtNumero);
        tela.add(txtNome);
        tela.add(txtPartido);

        btnSalvar = new JButton("Salvar");


        btnSalvar.setBounds(280, 25, 80, 20);

        tela.add(btnSalvar);


        btnLocalizar = new JButton("Localizar Candidato");
        btnLocalizar.setBounds(10, 180, 200, 20);



        tela.add(btnLocalizar);

        setSize(400, 250);
        setVisible(true);
        setLocationRelativeTo(null);

    btnSalvar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onClickSalvar();
        }
    });


    btnLocalizar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

                onClickLocalizar();

        }
    });




}

    private void onClickLocalizar()  {

        bar.setValue(bar.getMinimum());

        if (roda==null){
            roda = new roda();
            roda.start();
        }
    }





    private void onClickSalvar() {
    CandidatoController cc = new CandidatoController();
    try {
        cc.salvar(txtNumero.getText(),txtNome.getText(),txtPartido.getText());
        JOptionPane.showMessageDialog(this,
                "Candidato salvo com sucesso!");
        candidatoList = new CandidatoController().listarCandidatos();


    }catch (SQLException e){
        JOptionPane.showMessageDialog(this,
                "Nao foi possivel salvar Caandidato! " + e.getLocalizedMessage());

    }


    }

    class roda extends Thread{
        public void run(){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int valor = bar.getValue();
                    bar.setValue(valor+1);

                }

            };
            for (int i = 0; i < 100; i++) {
                // ---------------------------------
                // Faça aqui o processo a realizar
                // ---------------------------------
                // Atualiza a Barra de Progresso

                try {
                    SwingUtilities.invokeAndWait(runnable);
                } catch (java.lang.reflect.InvocationTargetException e) {
                    break;
                } catch (InterruptedException e) {
                }
            }
            InformacaoCand informacaoCand = new InformacaoCand();
            informacaoCand.setVisible(true);
            roda = null;
            dispose();
        }
    }


}




