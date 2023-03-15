package com.example.uaustore.models;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uaustore.ui.Item_anunciar;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private Double preco;
    private String nome;
    private String categoria;
    private Bitmap imagem_apresentacao;
    private Bitmap[] imagens;
    private Double starrate;
    private String descricao;
    private int quantidade;
    private int id = 0;
    private static int contadorID;
    private int vendedorID;
    private String datadeanuncio;
    private int vendidos;
    private Double preco_promo;
    static SQLiteDatabase DB_itens;
    static String[] avaliacoes;


    private static final List<Item> itens = new ArrayList<>();
    private static final List<Item> itensbusca = new ArrayList<>();

    @SuppressLint("Range")
    public static void atualizarID (Context context) {
        DB_itens = context.openOrCreateDatabase("db_itens",Context.MODE_PRIVATE,null);
        String consulta = "SELECT id FROM tb_itens ORDER BY id DESC LIMIT 1";
        Cursor cursor = DB_itens.rawQuery(consulta, null);
        cursor.moveToNext();

        try {
            Item.setContadorID(cursor.getInt(cursor.getColumnIndex("id")) + 1);
        } catch (Exception e){
            Item.setContadorID(1);
        }

        //String update = "UPDATE tb_contas SET id = (" + Conta.getContadorID() + ") WHERE id LIKE " + this.id + "";

        System.out.println(Conta.getContadorID());

    }


    public Item() {

    }


    public Item(Double preco, String nome, String categoria, Bitmap imagem_apresentacao,
                Bitmap[] imagens, Double starrate, String descricao, int quantidade, int id,
                int vendedorID, String datadeanuncio, int vendidos, Double preco_promo) {
        this.preco = preco;
        this.nome = nome;
        this.categoria = categoria;
        this.imagem_apresentacao = imagem_apresentacao;
        this.imagens = imagens;
        this.starrate = starrate;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.id = id;
        this.vendedorID = vendedorID;
        this.datadeanuncio = datadeanuncio;
        this.vendidos = vendidos;
        this.preco_promo = preco_promo;
    }


    public Item(Double preco, String nome, String categoria, Bitmap imagem_apresentacao, Bitmap[] imagens, Double starrate, String descricao) {
        this.preco = preco;
        this.nome = nome;
        this.categoria = categoria;
        this.imagem_apresentacao = imagem_apresentacao;
        this.imagens = imagens;
        this.descricao = descricao;
        this.starrate = starrate;
        this.quantidade = quantidade;
        this.id = id;
        this.vendedorID = vendedorID;
        this.datadeanuncio = Dataatual.dataatual();
    }

    public Item(Double preco, String nome, String categoria, Bitmap imagem_apresentacao, Double starrate, String descricao) {
        this.preco = preco;
        this.nome = nome;
        this.categoria = categoria;
        this.imagem_apresentacao = imagem_apresentacao;
        this.imagens = imagens;
        this.starrate = starrate;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.id = id;
        this.vendedorID = vendedorID;
    }

    public String getDatadeanuncio() {
        return datadeanuncio;
    }

    public void setDatadeanuncio(String datadeanuncio) {
        this.datadeanuncio = datadeanuncio;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
        updateSqlite("vendidos", vendidos+"");
    }

    public void setSqlite (String coluna, String valor){
        String set = "INSERT INTO tb_itens ('" + coluna + "',id) VALUES('" + valor + "', " + this.id + ")";
        DB_itens.execSQL(set);
    }

    public void updateSqlite (String coluna, String valor){
        String sql = "UPDATE tb_itens SET " + coluna + " = '" + valor + "' WHERE id = '" + this.id + "'";
        DB_itens.execSQL(sql);
    }

    @SuppressLint("Range")
    public String getSqlite (String coluna){
        String cunsulta = "SELECT " + coluna + " FROM tb_itens WHERE id LIKE '" + this.id + "'";
        Cursor cursor = DB_itens.rawQuery(cunsulta, null);
        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndex(coluna));


    }

    public void updateSqliteFoto (Bitmap bitmap, String coluna){
        String sql = "UPDATE tb_itens SET imagem_perfil = '" + getBitmapAsByteArray(bitmap) + "' WHERE id = '" + this.id + "'";
        DB_itens.execSQL(sql);
    }

    static void inicartabelaebanco(Context context) {
        DB_itens = context.openOrCreateDatabase("db_itens", Context.MODE_PRIVATE, null);
        String createTable = "CREATE TABLE IF NOT EXISTS tb_itens (nome VARCHAR, preco DOUBLE, preco_promo DOUBLE, " +
                "categoria VARCHAR, imagem_apresentacao BLOB, imagem1 BLOB, imagem2 BLOB, imagem3 BLOB, " +
                "starrate DOUBLE, descricao VARCHAR, quantidade INT, id INT, vendedorID INT, datadeanuncio VARCHAR, vendidos INT)";
        DB_itens.execSQL(createTable);
    }


    public Item(String nome, Number preco, Double preco_promo, String categoria, Bitmap imagem_apresentacao, Bitmap imagem1,
                Bitmap imagem2, Bitmap imagem3, String descricao, int quantidade, Context context) {

        try {
            Item.atualizarID(context);
        } catch (Exception e){
            Item.setContadorID(Item.getContadorID() + 1);
        }

        DB_itens = context.openOrCreateDatabase("db_itens",Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS tb_itens (nome VARCHAR, preco DOUBLE, preco_promo DOUBLE, " +
                "categoria VARCHAR, imagem_apresentacao BLOB, imagem1 BLOB, imagem2 BLOB, imagem3 BLOB, " +
                "starrate DOUBLE, descricao VARCHAR, quantidade INT, id INT, vendedorID INT, datadeanuncio VARCHAR, vendidos INT, avaliacaoes INT)";
        DB_itens.execSQL(createTable);

        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("preco", (Double) preco);
        values.put("preco_promo", preco_promo);
        values.put("categoria", categoria);
        values.put("imagem_apresentacao", getBitmapAsByteArray(imagem_apresentacao));
        values.put("imagem1", getBitmapAsByteArray(imagem1));
        values.put("imagem2", getBitmapAsByteArray(imagem2));
        values.put("imagem3", getBitmapAsByteArray(imagem3));
        values.put("descricao", descricao);
        values.put("quantidade", quantidade);
        values.put("starrate", 0);
        values.put("id", Item.getContadorID());
        values.put("vendedorID", ContaLogada.getContaLogada().getId());
        values.put("datadeanuncio", Dataatual.dataatual());
        values.put("vendidos", 0);
        DB_itens.insert("tb_itens", null, values);
        ContaLogada.getContaLogada().anunciar();

        //System.out.println(ContaLogada.id);

    }

    static Cursor getCursor(){
        String consulta = "SELECT preco, preco_promo, nome, categoria, imagem_apresentacao, imagem1, imagem2, imagem3, starrate, descricao, quantidade, id" +
                ", vendedorID, datadeanuncio, vendidos FROM tb_itens";
        return DB_itens.rawQuery(consulta, null);
    }

    static int[] getIcoisas(Cursor cursor){

        return new int[] {cursor.getColumnIndex("preco"),
                cursor.getColumnIndex("nome"),
                cursor.getColumnIndex("categoria"),
                cursor.getColumnIndex("imagem_apresentacao"),
                cursor.getColumnIndex("imagem1"),
                cursor.getColumnIndex("imagem2"),
                cursor.getColumnIndex("imagem3"),
                cursor.getColumnIndex("starrate"),
                cursor.getColumnIndex("descricao"),
                cursor.getColumnIndex("quantidade"),
                cursor.getColumnIndex("id"),
                cursor.getColumnIndex("vendedorID"),
                cursor.getColumnIndex("datadeanuncio"),
                cursor.getColumnIndex("vendidos"),
                cursor.getColumnIndex("preco_promo")


        };
    }

    public static List<Item> getItens(Context context, String categoria){
        List<Item> listaCategoria = new ArrayList<>();
        for (Item item:
             getItens(context)) {
            if (item.getCategoria().equals(categoria))
                listaCategoria.add(item);
        }
        return listaCategoria;
    }

    public static List<Item> getItensMatrix(Context context, List<String> listaID){

        List<Item> listaItens = new ArrayList<>();
        listaID.remove(0);

        for (String id:
             listaID) {

            for (Item item1:
                 getItens(context)) {

                if (item1.getId() == Integer.parseInt(id)){
                    listaItens.add(item1);
                    break;
                }

            }

        }
        return listaItens;

    }

    public static List<Item> getItens(Context context, List<String> listaID) {

        List<Item> listaItensVendedor = new ArrayList<>();
        listaID.remove(0);




            for (Item item1:
                    getItens(context)){
                if (listaID.contains(item1.getId()+"")){
                    listaItensVendedor.add(item1);

                    if (listaItensVendedor.size() == listaID.size())
                        break;
                }

            }


                return listaItensVendedor;





    }

    public static Item getItem (Context context, int id){

        inicartabelaebanco(context);
        for (Item item:
             getItens(context)) {
            if (item.getId() == id)
                return item;

        }

        return null;
    }

    public static List<Item> getItensCat(Context context, String categoria) {

        inicartabelaebanco(context);
        String consulta = "SELECT preco, preco_promo, nome, categoria, imagem_apresentacao, imagem1, imagem2, imagem3, starrate, descricao, quantidade, id" +
                ", vendedorID, datadeanuncio, vendidos FROM tb_itens WHERE categoria LIKE '" + categoria + "'";
        Cursor cursor = DB_itens.rawQuery(consulta, null);


        itensbusca.clear();
        itens.clear();
        cursor.moveToFirst();
        List<Item> itens = new ArrayList<>();

        int[] icoisas = getIcoisas(cursor);


        while (cursor != null) {

            try {

                byte[] imagem1 = cursor.getBlob(icoisas[4]);
                byte[] imagem_apresentacao = cursor.getBlob(icoisas[3]);
                byte[] imagem2 = cursor.getBlob(icoisas[5]);
                byte[] imagem3 = cursor.getBlob(icoisas[6]);

                Bitmap[] array_imagens = {
                        BitmapFactory.decodeByteArray(imagem1, 0, imagem1.length),
                        BitmapFactory.decodeByteArray(imagem2, 0, imagem2.length),
                        BitmapFactory.decodeByteArray(imagem3, 0, imagem3.length)

                };


                Item item = new Item(cursor.getDouble(icoisas[0]),
                        cursor.getString(icoisas[1]),
                        cursor.getString(icoisas[2]),
                        BitmapFactory.decodeByteArray(imagem_apresentacao, 0, imagem_apresentacao.length),
                        array_imagens,
                        cursor.getDouble(icoisas[7]),
                        cursor.getString(icoisas[8]),
                        cursor.getInt(icoisas[9]),
                        cursor.getInt(icoisas[10]),
                        cursor.getInt(icoisas[11]),
                        cursor.getString(icoisas[12]),
                        cursor.getInt(icoisas[13]),
                        cursor.getDouble(icoisas[14]));

                itens.add(item);
                cursor.moveToNext();
            } catch (Exception e) {
                return itens;
            }


        }
        return null;
    }



    public static List<Item> getItens(Context context) {
        inicartabelaebanco(context);



        Cursor cursor = getCursor();
        itensbusca.clear();
        itens.clear();
        cursor.moveToFirst();
        List<Item> itens = new ArrayList<>();

        int[] icoisas = getIcoisas(cursor);


        while (cursor != null) {

            try {

                byte[] imagem1 = cursor.getBlob(icoisas[4]);
                byte[] imagem_apresentacao = cursor.getBlob(icoisas[3]);
                byte[] imagem2 = cursor.getBlob(icoisas[5]);
                byte[] imagem3 = cursor.getBlob(icoisas[6]);

                Bitmap[] array_imagens = {
                        BitmapFactory.decodeByteArray(imagem1, 0, imagem1.length),
                        BitmapFactory.decodeByteArray(imagem2, 0, imagem2.length),
                        BitmapFactory.decodeByteArray(imagem3, 0, imagem3.length)

                };


                Item item = new Item(cursor.getDouble(icoisas[0]),
                        cursor.getString(icoisas[1]),
                        cursor.getString(icoisas[2]),
                        BitmapFactory.decodeByteArray(imagem_apresentacao, 0, imagem_apresentacao.length),
                        array_imagens,
                        cursor.getDouble(icoisas[7]),
                        cursor.getString(icoisas[8]),
                        cursor.getInt(icoisas[9]),
                        cursor.getInt(icoisas[10]),
                        cursor.getInt(icoisas[11]),
                        cursor.getString(icoisas[12]),
                        cursor.getInt(icoisas[13]),
                        cursor.getDouble(icoisas[14]));

                itens.add(item);
                cursor.moveToNext();
            } catch (Exception e) {
                return itens;
            }


        }
        return null;
    }




    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        updateSqlite("quantidade",quantidade+"");
        this.quantidade = quantidade;
    }

    public int getVendedorID() {
        return vendedorID;
    }

    public void setVendedorID(int vendedorID) {
        this.vendedorID = vendedorID;
        updateSqlite("vendedor", vendedorID+"");
    }


    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
        updateSqlite("preco",preco+"");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        updateSqlite("nome", nome);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
        updateSqlite("categoria", categoria);
    }

    public Double getPreco_promo() {
        return preco_promo;
    }

    public void setPreco_promo(Double preco_promo) {
        this.preco_promo = preco_promo;
        updateSqlite("preco_promo",preco_promo+"");
    }

    public Bitmap getImagem_apresentacao() {
        return imagem_apresentacao;
    }

    public void setImagem_apresentacao(Bitmap imagem_apresentacao) {
        this.imagem_apresentacao = imagem_apresentacao;
        updateSqliteFoto( imagem_apresentacao,"imagem_apresentacao");
    }

    public Bitmap[] getImagens() {
        return imagens;
    }



    public void setImagem1(Bitmap bitmap){
        this.imagens[0] = bitmap;
        updateSqliteFoto(bitmap,"imagem1");
    }

    public void setImagem2(Bitmap bitmap){
        this.imagens[1] = bitmap;
        updateSqliteFoto(bitmap,"imagem2");

    }

    public void setImagem3(Bitmap bitmap){
        this.imagens[2] = bitmap;
        updateSqliteFoto(bitmap,"imagem3");

    }

    public int getQuantidadeAvaliacoes(){
        avaliacoes = getSqlite("starrate").split(" ");
        return avaliacoes.length;
    }

    public Double getStarrate() {
        avaliacoes = getSqlite("starrate").split(" ");
        Double soma = 0.0;
        for (String a:
             avaliacoes) {

            soma += Double.parseDouble(a);

        }
        System.out.println(soma);
        System.out.println(avaliacoes.length-1);
        return soma/Double.parseDouble(avaliacoes.length-1+"");
    }

    public void setStarrate(Double starrate) {
        updateSqlite("starrate", getSqlite("starrate") + " " + starrate);
        //String[] string = ContaLogada.getContaLogada().getCompras_data().split("@");
       // ContaLogada.getContaLogada().setCompras_data(starrate + "@" + string[1]);
        //System.out.println(getStarrate());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        updateSqlite("descricao", descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContadorID() {
        return contadorID;
    }

    public static void setContadorID(int contadorID) {
        Item.contadorID = contadorID;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (id == Integer.parseInt((String) obj)){
            return true;
        } else {
            return false;
        }
    }




}
