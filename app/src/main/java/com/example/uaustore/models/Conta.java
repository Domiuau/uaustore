package com.example.uaustore.models;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static android.content.Context.MODE_PRIVATE;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Conta {


    private String usuario;
    private String email;
    private String senha;
    private String nome;
    private List<String> comprados;
    private List<String> anunciados;
    private String favoritos;
    private String carrinho;
    private boolean adm;
    private int id;
    private static int contadorID = 0;
    private Bitmap foto_perfil;
    private boolean privada;
    private int vendas;
    private Double saldo;
    private String datacriacao;
    private static Context context;
    static SQLiteDatabase DB_contas;
    private String compras_data;

    public Conta(String usuario, String email, String senha, String nome, List<String> comprados, List<String> anunciados, boolean adm, int id, Bitmap foto_perfil, boolean privada, int vendas, Double saldo, String datacriacao,Context context, String compras_data, String favoritos, String carrinho) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.comprados = comprados;
        this.anunciados = anunciados;
        this.adm = adm;
        this.id = id;
        this.foto_perfil = foto_perfil;
        this.privada = privada;
        this.vendas = vendas;
        this.saldo = saldo;
        this.datacriacao = datacriacao;
        this.compras_data = compras_data;
        this.favoritos = favoritos;
        this.carrinho = carrinho;
        this.context = context;
        DB_contas = context.openOrCreateDatabase("DB_Contas",MODE_PRIVATE,null);
    }

    //public Conta(String usuario, String email, String senha, String nome, List<String> comprados, List<String> anunciados, boolean adm, boolean privada,int id, Bitmap foto_perfil,  String datacriacao, int vendas, double saldo, Context context) {
    public Conta(String usuario, String email, String senha, String nome, boolean privada,  Bitmap foto_perfil, Context context) {

        DB_contas = context.openOrCreateDatabase("DB_Contas",MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS tb_contas (usuario VARCHAR, senha VARCHAR, nome VARCHAR, email VARCHAR, comprados VARCHAR, anunciados VARCHAR, adm BOOLEAN, privada BOOLEAN, id INT, imagem_perfil BLOB, datacriacao VARCHAR, vendas INT, saldo DOUBLE, compras_data VARCHAR, favoritos VARCHAR, carrinho VARCHAR)";
        DB_contas.execSQL(createTable);

        atualizarID();
        this.context = context;
        ContentValues values = new ContentValues();
        values.put("usuario", usuario);
        values.put("senha", senha);
        values.put("nome", nome);
        values.put("email", email);
        values.put("adm", 0);
        if (privada)
            values.put("privada", 1);
        else
            values.put("privada", 0);
        values.put("comprados", "");
        values.put("anunciados", "");
        values.put("favoritos", "");
        values.put("carrinho", "");
        values.put("id", Conta.getContadorID());
        values.put("imagem_perfil", getBitmapAsByteArray(foto_perfil));
        values.put("datacriacao", Dataatual.dataatual());
        values.put("vendas", 0);
        values.put("saldo", 100000);
        values.put("compras_data", "");
        DB_contas.insert("tb_contas", null, values);
        logar(usuario,senha,context);

    }



    public static Conta getConta(int id, Context context) {
        String consulta = "SELECT usuario, senha, nome, email, comprados, anunciados, adm, privada, id, imagem_perfil, datacriacao, vendas, saldo, compras_data, favoritos, carrinho FROM tb_contas WHERE id LIKE " + id + "";
        //String consulta = "SELECT usuario, senha, nome, email, id FROM contas WHERE id LIKE '" + id + "'";
        Cursor cursor = DB_contas.rawQuery(consulta, null);
        String createTable = "CREATE TABLE IF NOT EXISTS tb_contas (usuario VARCHAR, senha VARCHAR, nome VARCHAR, email VARCHAR, comprados VARCHAR, anunciados VARCHAR, adm BOOLEAN, privada BOOLEAN, id INT, imagem_perfil BLOB, datacriacao VARCHAR, vendas INT, saldo DOUBLE, compras_data VARCHAR, anunciados VARCHAR, carrinho VARCHAR)";
        DB_contas.execSQL(createTable);
        cursor.moveToNext();
        DB_contas = context.openOrCreateDatabase("DB_Contas",MODE_PRIVATE,null);
        int[] icoisas = {cursor.getColumnIndex("usuario"),
                cursor.getColumnIndex("email"),
                cursor.getColumnIndex("senha"),
                cursor.getColumnIndex("nome"),
                cursor.getColumnIndex("comprados"),
                cursor.getColumnIndex("anunciados"),
                cursor.getColumnIndex("adm"),
                cursor.getColumnIndex("privada"),
                cursor.getColumnIndex("id"),
                cursor.getColumnIndex("imagem_perfil"),
                cursor.getColumnIndex("datacriacao"),
                cursor.getColumnIndex("vendas"),
                cursor.getColumnIndex("saldo"),
                cursor.getColumnIndex("compras_data"),
                cursor.getColumnIndex("anunciados"),
                cursor.getColumnIndex("favoritos"),
                cursor.getColumnIndex("carrinho")


        };

        List<String> list_comprados = new ArrayList<>();
        List<String> list_anunciados = new ArrayList<>();

        try {
            list_comprados.addAll(Arrays.asList(cursor.getString(icoisas[4]).split(" ")));

        } catch (Exception e) {


        }

        try {
            list_anunciados.addAll(Arrays.asList(cursor.getString(icoisas[5]).split(" ")));

        } catch (Exception e) {

        }

        Boolean adm = false;
        Boolean privada = false;
        if (cursor.getInt(icoisas[6]) == 1)
            adm = true;


        if (cursor.getInt(icoisas[7]) == 1)
            privada = true;

        byte[] image = cursor.getBlob(icoisas[9]);


        return new Conta(cursor.getString(icoisas[0]),
                cursor.getString(icoisas[1]),
                cursor.getString(icoisas[2]),
                cursor.getString(icoisas[3]),
                list_comprados,
                list_anunciados,
                adm,
                cursor.getInt(icoisas[8]),
                BitmapFactory.decodeByteArray(image, 0, image.length),
                privada,
                cursor.getInt(icoisas[11]),
                cursor.getDouble(icoisas[12]),
                cursor.getString(icoisas[10]),
                context,
                cursor.getString(icoisas[13]),
                cursor.getString(icoisas[14]),
                cursor.getString(icoisas[15])





        );
    }
    //public Conta(String usuario, String email, String senha, String nome, List<String> comprados, List<String> anunciados, boolean adm, int id, Bitmap foto_perfil, boolean privada, int vendas, Double saldo, String datacriacao) {
        @SuppressLint("Range")
        public static void atualizarID() {

            String query = "SELECT id FROM tb_contas ORDER BY id DESC LIMIT 1";
            Cursor cursor = DB_contas.rawQuery(query, null);
            cursor.moveToNext();

            try {
                Conta.setContadorID(cursor.getInt(cursor.getColumnIndex("id")) + 1);
            } catch (Exception e){
                Conta.setContadorID(1);
            }

            //String update = "UPDATE tb_contas SET id = (" + Conta.getContadorID() + ") WHERE id LIKE " + this.id + "";

            System.out.println(Conta.getContadorID());

        }




    public static void setIdContaLogada(int id){
        String createTableContaLogadaID = "CREATE TABLE IF NOT EXISTS TableContaLogadaID (contaID INT)";
        DB_contas.execSQL(createTableContaLogadaID);
        String deleteID = "DELETE FROM TableContaLogadaID";
        String insertID = "INSERT INTO TableContaLogadaID (contaID) VALUES (" + id + ") ";
        DB_contas.execSQL(deleteID);
        DB_contas.execSQL(insertID);
    }

    @SuppressLint("Range")
    public static int getIdContaLogada(Context context){
        DB_contas = context.openOrCreateDatabase("DB_Contas",MODE_PRIVATE,null);
        String consulta = "SELECT contaID FROM TableContaLogadaID";
        Cursor cursor = DB_contas.rawQuery(consulta,null);
        cursor.moveToNext();
        return cursor.getInt(cursor.getColumnIndex("contaID"));

    }

        @SuppressLint("Range")
        public static void logar(String usuarioouemail, String senha, Context context){
            System.out.println("LOGANDO");
            DB_contas = context.openOrCreateDatabase("DB_Contas",MODE_PRIVATE,null);
            String consulta = "SELECT id FROM tb_contas WHERE email LIKE '" + usuarioouemail + "' AND senha LIKE '" + senha + "' OR usuario LIKE '" + usuarioouemail + "' AND senha LIKE '" + senha + "'";
                    Cursor cursor = DB_contas.rawQuery(consulta,null);
                    cursor.moveToNext();
                    ContaLogada.criarContaLogada(getConta(cursor.getInt(cursor.getColumnIndex("id")),context));
                    setIdContaLogada(cursor.getInt(cursor.getColumnIndex("id")));
        }

    public void anunciar(){

        setAnunciados(Item.getContadorID()+"");
        //ContaLogada.getContaLogada().setAnunciados(item.getId()+"");
    }

    public void comprar(Item item){

        Conta vendedor = getConta(item.getVendedorID(),context);

        if (item.getPreco_promo() > 0){

            setSaldo(getSaldo() - item.getPreco_promo());
            vendedor.setSaldo(vendedor.getSaldo() + item.getPreco_promo());

        } else {

            setSaldo(getSaldo() - item.getPreco());
            vendedor.setSaldo(vendedor.getSaldo() + item.getPreco());
        }

        setComprados(item.getId()+"");
        item.setVendidos(item.getVendidos() + 1);
        item.setQuantidade(item.getQuantidade() - 1);
        vendedor.setVendas(getVendas() + 1);
        //item.setDatadeanuncio(Dataatual.dataatual());
        setCompras_data();

    }




        public void setSqlite (String coluna, String valor){
            String set = "INSERT INTO tb_contas ('" + coluna + "',id) VALUES('" + valor + "', " + this.id + ")";
            DB_contas.execSQL(set);
        }

        public void updateSqlite (String coluna, String valor){
            String sql = "UPDATE tb_contas SET " + coluna + " = '" + valor + "' WHERE id = '" + this.id + "'";
            DB_contas.execSQL(sql);
        }

        public void updateSqliteFoto (Bitmap bitmap){
            String sql = "UPDATE tb_contas SET imagem_perfil = '" + getBitmapAsByteArray(bitmap) + "' WHERE id = '" + this.id + "'";
            DB_contas.execSQL(sql);
        }

        @SuppressLint("Range")
        public String getSqlite (String coluna){
// WHERE id LIKE '" + this.id + "'
            String cunsulta = "SELECT " + coluna + " FROM tb_contas WHERE id LIKE '" + this.id + "'";
            Cursor cursor = DB_contas.rawQuery(cunsulta, null);
            cursor.moveToNext();
            return cursor.getString(cursor.getColumnIndex(coluna));


        }

    public String getCompras_data() {
        return getSqlite("compras_data");
    }

    public void setCompras_data() {
        if (!getCompras_data().endsWith("¬")){
            updateSqlite("compras_data", getCompras_data() + "¬null@" +Dataatual.dataatual());
        }
        else {
            updateSqlite("compras_data", getCompras_data() + "null@" +Dataatual.dataatual());
        }

    }


    public int getVendas () {
            return Integer.parseInt(getSqlite("vendas"));
        }

        public void setVendas (int vendas){
            updateSqlite("vendas",vendas+"");
            this.vendas = vendas;
        }

        public Double getSaldo () {
            return Double.parseDouble(getSqlite("saldo"));
        }

        public void setSaldo ( double saldo){
            this.saldo = saldo;
            updateSqlite("saldo",saldo+"");
        }

        public String getUsuario () {
            return this.usuario;
        }

        public void setUsuario (String usuario){
            this.usuario = usuario;
            updateSqlite("usuario", usuario);
        }

        public String getEmail () {
            return getSqlite("email");
        }

        public void setEmail (String email){
            this.email = email;
            updateSqlite("email", email);
        }

        public String getSenha () {
            return this.senha;
        }

        public void setSenha (String senha){
            this.senha = senha;
            updateSqlite("senha",senha);
        }

        public String getNome () {
            return this.nome;
        }

        public void setNome (String nome){
            this.nome = nome;
            updateSqlite("nome",nome);
        }

        public List<String> getComprados () {
            return new ArrayList<>(Arrays.asList(getSqlite("comprados").split(" ")));


        }

        public void setComprados (String idcomprado) {
            comprados.add(idcomprado);
            updateSqlite("comprados", comprados.toString().replace("[","").replace("]","").replace(",",""));
        }

        public List<String> getAnunciados () {
            return new ArrayList<>(Arrays.asList(getSqlite("anunciados").split(" ")));
        }

    public List<String> getAnunciados (Item item) {
        return new ArrayList<>(Arrays.asList(getSqlite("anunciados").replace(" " + item.getId(),"") .split(" ")));
    }

    public ArrayList<String> getFavoritos() {

        return new ArrayList<String>(Arrays.asList(getSqlite("favoritos").split(" ")));
    }

    public void setFavoritos(int itemId) {
        updateSqlite("favoritos", getSqlite("favoritos") + " " + itemId);
    }

    public void setFavoritosRemover(int itemId) {
        updateSqlite("favoritos", getFavoritos().toString().replace("[","").replace("]","").replace(",","").replace(" " + itemId,""));
    }

    public void setCarrinhoRemover(int itemId) {
        updateSqlite("carrinho", getCarrinho().toString().replace("[","").replace("]","").replace(",","").replace(" " + itemId,""));
    }



    public List<String> getCarrinho() {

        return new ArrayList<String>(Arrays.asList(getSqlite("carrinho").split(" ")));
    }

    public void setCarrinho(int itemId) {
        updateSqlite("carrinho", getSqlite("carrinho") + " " + itemId);
    }

    public void setAnunciados (String iditem) {
            anunciados.add(iditem);
            updateSqlite("anunciados", anunciados.toString().replace("[","").replace("]","").replace(",",""));
        }

        public boolean isAdm () {
            return adm;
        }

        public void setAdm (boolean adm){
            this.adm = adm;
            if (adm == true) {
                updateSqlite("adm", "1");
            } else {
                updateSqlite("adm", "0");
            }
        }

    public void setCompras_data(String compras_data) {
        updateSqlite("compras_data",compras_data);
    }

    public int getId () {
            return this.id;
        }

        //deletar dps provavelmente nao vou usar
        private void setId (int id){
            this.id = id;
        }

        public static int getContadorID () {
            return contadorID;
        }

        public static void setContadorID (int contadorID){
            Conta.contadorID = contadorID;
        }

        public Bitmap getFoto_perfil () {
            return foto_perfil;
        }

        public void setFoto_perfil (Bitmap foto_perfil){
        updateSqliteFoto(foto_perfil);
            this.foto_perfil = foto_perfil;
        }

        public boolean isPrivada () {
            return this.privada;
        }

        public void setPrivada ( boolean privada){
            this.privada = privada;
            if (privada)
                updateSqlite("adm","1");
            else
                updateSqlite("adm","0");
        }

        public String getDatacriacao () {
            return this.datacriacao;
        }

        public void setDatacriacao (String datacriacao){
            this.datacriacao = datacriacao;
        }

        public static byte[] getBitmapAsByteArray (Bitmap bitmap){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            System.out.println(outputStream.toByteArray());
            return outputStream.toByteArray();
        }

    }
