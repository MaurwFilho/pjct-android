package com.example.quiz;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    int respostaCerta = R.id.rbResposta1;
    RadioGroup rgRespostas;
    int pontos = 0;

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("Quem foi o campeão da Liga dos Campeões da UEFA de 2019?", R.id.rbResposta1, "Liverpool", "Ajax", "Barcelona", "Real Madrid"));
            add(new Questao("Quem é considerado o melhor jogador de futebol de todos os tempos?", R.id.rbResposta2, "Cruyff", "Pelé", "Zidane", "Maradona"));
            add(new Questao("Quantos mudial o Palmeiras tem?", R.id.rbResposta3, "4", "2", "0", "1"));
            add(new Questao("Onde surgiu o futebol?", R.id.rbResposta4, "Brasil", "França", "Estados Unidos", "Inglaterra"));
            add(new Questao("Qual o maior publico em uma partida de futebol?", R.id.rbResposta4, "99.017", "125.541", "66.358", "199.854"));
            add(new Questao("Quem é o maior artilheiro da história das copas mundiais?", R.id.rbResposta2, "Pelé", "Marta", "Messi", "Soteldo"));
            add(new Questao("Quantos gols o Pelé fez?", R.id.rbResposta4, "1.000", "5.412", "799", "1.281"));
            add(new Questao("Quem é considerado o rei do drible?", R.id.rbResposta1, "Ronaldinho", "Neymar", "Maradona", "Garrincha"));
        }
    };

    private void carregarQuestao(){
        if(questoes.size() > 0) {
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);
        }
        else{ //acabaram as questões
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        pergunta = (TextView)findViewById(R.id.pergunta);
        rbResposta1 = (RadioButton)findViewById(R.id.rbResposta1);
        rbResposta2 = (RadioButton)findViewById(R.id.rbResposta2);
        rbResposta3 = (RadioButton)findViewById(R.id.rbResposta3);
        rbResposta4 = (RadioButton)findViewById(R.id.rbResposta4);
        rgRespostas = (RadioGroup)findViewById(R.id.rgRespostas);
        carregarQuestao();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }

    public void btnResponderOnClick(View v){
        RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }
}
