package br.com.controller;

import br.com.model.Md5;
import br.com.model.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * Created by lucas on 21/09/16.
 */
public class PasswordADM {
    @FXML
    private PasswordField PFsenhaatual;
    @FXML
    private PasswordField PFsenha;
    @FXML
    private PasswordField PFconfsenha;
    @FXML
    private Label LBuser;
    Usuario u = new Usuario();


    public void setUser(Usuario u){
        this.u = u;
        LBuser.setText(u.getUsuario());
    }

    public void onSalvar(ActionEvent event) {
        if (PFsenhaatual.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite a senha atual!");
            a.show();
        }
        if (PFconfsenha.getText().length() < 6){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite uma senha com no mínimo 6 dígitos!");
            a.show();
        }
        if (!PFsenha.getText().equals(PFconfsenha.getText())){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Senhas não batem!");
            a.show();
        }

        else {
            UsuarioDao ud = new UsuarioDao();
            if (ud.loga(u.getUsuario(), u.getSenha()) == null){
                Alert e = new Alert(Alert.AlertType.INFORMATION);
                e.setHeaderText("ATENÇÃO");
                e.setContentText("Senha incorreta!!");
                e.show();
            }
            else {
                u.setSenha(Md5.md5(PFconfsenha.getText()));
                ud.alterarSenha(u);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    public void onClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
