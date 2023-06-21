# jdbc-swing-formula1

Projeto em Java utilizando Swing e JDBC.
Simula um programa para atualizar um banco de dados de uma competição automobilística, como a Formula 1.
O projeto consiste em uma Janela Principal e 3 Janelas Secundárias (Equipe, Piloto, Corrida).
Na criação do banco de dados com o script SQL, a tabela Piloto possui uma chave estrangeira 'EquipeID' que referencia a ID da tabela Equipe e a tabela Corrida possui VencedorId que referencia ID na tabela Piloto.
Nas janelas é possível Consultar, Gravar, Atualizar e Apagar registros nas tabelas, sempre seguindo as restrições do banco de dados.
