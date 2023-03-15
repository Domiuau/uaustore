package com.example.uaustore.models;

public class ContaLogada{

    private static Conta contaLogada;
    private static Item item_comprar;

    public static void criarContaLogada(Conta conta) {
        contaLogada = conta;

    }

    public static void atualizarContaLogada(Conta contaatualizada){
        contaLogada = contaatualizada;
    }

    public static void setItem_comprar(Item item) {
        item_comprar = item;

    }

    public static Conta getContaLogada() {
        return contaLogada;
    }

    public static Item getItem_comprar() {
        return item_comprar;
    }
}
