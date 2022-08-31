/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.Entidades;

/**
 *
 * @author Alex T. Rivera
 */
public class Item {
    
    private int id_item;
    private int plazo;
    private int disponible;
    private int stock;
    private int reservado;
    private int codigo_nivel;
    private String identificador;
    private int cantidad;
    private double precio_pedido;
    private double precio_almacen;
    private String nombre;
    private String tamaño_lote;
    private Item oItem;
    private int id_plan;
    private String imagen;
    private String RecepProgramadas;
    private String Semana_RPP;

    public Item() {
    }

    public Item(int id_item) {
        this.id_item = id_item;
    }

    public Item(int id_item, int id_plan) {
        this.id_item = id_item;
        this.id_plan = id_plan;
    }

    public Item(int id_item, String identificador) {
        this.id_item = id_item;
        this.identificador = identificador;
    }
    
    public Item(int id_item, int plazo, int disponible, int stock, int reservado, int codigo_nivel, String indentificador, int cantidad, double precio_pedido, double precio_almacen, String nombre, String tamaño_lote, Item oItem, int id_plan, String imagen) {
        this.id_item = id_item;
        this.plazo = plazo;
        this.disponible = disponible;
        this.stock = stock;
        this.reservado = reservado;
        this.codigo_nivel = codigo_nivel;
        this.identificador = indentificador;
        this.cantidad = cantidad;
        this.precio_pedido = precio_pedido;
        this.precio_almacen = precio_almacen;
        this.nombre = nombre;
        this.tamaño_lote = tamaño_lote;
        this.oItem = oItem;
        this.id_plan = id_plan;
        this.imagen = imagen;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public int getCodigo_nivel() {
        return codigo_nivel;
    }

    public void setCodigo_nivel(int codigo_nivel) {
        this.codigo_nivel = codigo_nivel;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_pedido() {
        return precio_pedido;
    }

    public void setPrecio_pedido(double precio_pedido) {
        this.precio_pedido = precio_pedido;
    }

    public double getPrecio_almacen() {
        return precio_almacen;
    }

    public void setPrecio_almacen(double precio_almacen) {
        this.precio_almacen = precio_almacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño_lote() {
        return tamaño_lote;
    }

    public void setTamaño_lote(String tamaño_lote) {
        this.tamaño_lote = tamaño_lote;
    }

    public Item getoItem() {
        return oItem;
    }

    public void setoItem(Item oItem) {
        this.oItem = oItem;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRecepProgramadas() {
        return RecepProgramadas;
    }

    public void setRecepProgramadas(String RecepProgramadas) {
        this.RecepProgramadas = RecepProgramadas;
    }

    public String getSemana_RPP() {
        return Semana_RPP;
    }

    public void setSemana_RPP(String Semana_RPP) {
        this.Semana_RPP = Semana_RPP;
    }
    
    @Override
    public String toString() {
        return this.identificador;
    }
    
}
