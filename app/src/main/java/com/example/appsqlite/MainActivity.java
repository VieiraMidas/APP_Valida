package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtNome, txtCPF, txtRG, txtTelefone, txtSenha, txtConfirmaSenha;
    Button btnRegistar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtNome = findViewById(R.id.idnome);
        txtCPF = findViewById(R.id.idcpf);
        txtRG = findViewById(R.id.idrg);
        txtTelefone = findViewById(R.id.idtelefone);
        txtSenha = findViewById(R.id.idsenha);
        txtConfirmaSenha = findViewById(R.id.idconfirmarsenha);

        btnLogin = findViewById(R.id.idBtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, cpf, rg, telefone, email, senha, confirmaSenha;

                nome = txtNome.getText().toString();
                cpf = txtCPF.getText().toString();
                rg = txtRG.getText().toString();
                telefone = txtTelefone.getText().toString();
                email = txtCPF.getText().toString();
                senha = txtSenha.getText().toString();
                confirmaSenha = txtConfirmaSenha.getText().toString();

                if (nome.equals("") || cpf.equals("") || rg.equals("") || telefone.equals("") || email.equals("") || senha.equals("") || confirmaSenha.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean checharCPF = db.validarCPF(cpf);
                        if (checharCPF == true) {
                            Boolean inserir = db.insert(nome, cpf, rg, telefone, email, senha);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "CPF inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
