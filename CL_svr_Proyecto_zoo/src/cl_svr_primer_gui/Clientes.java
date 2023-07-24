/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl_svr_primer_gui;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nando
 */
public class Clientes {
    

    private static String cedula, nombre, direccion, telefono, email, provincia;
    private static int edad;

    public static boolean camposVacios() {
        if ((cedula.equals(""))
                || (nombre.equals(""))
                || (direccion.equals(""))
                || (telefono.equals(""))
                || (email.equals(""))
                || (provincia.equals(""))
                || (edad == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void agregar() {
        try {
            DataOutputStream d = new DataOutputStream(
                    new FileOutputStream("clientes.dat", true));
            cedula = JOptionPane.showInputDialog("Digite la cedula del cliente: ");
            nombre = JOptionPane.showInputDialog("Digite el nombre del cliente: ");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Digite la edad del cliente: "));
            direccion = JOptionPane.showInputDialog("Digite la direccion del cliente: ");
            telefono = JOptionPane.showInputDialog("Digite el telefono del cliente: ");
            email = JOptionPane.showInputDialog("Digite el email del cliente: ");
            provincia = JOptionPane.showInputDialog("Digite la provincia del cliente: ");
            d.writeUTF(cedula);
            d.writeUTF(nombre);
            d.writeInt(edad);
            d.writeUTF(direccion);
            d.writeUTF(telefono);
            d.writeUTF(email);
            d.writeUTF(provincia);
            JOptionPane.showMessageDialog(null, "Datos agregados correctamente :)",
                    "Agregar Datos", JOptionPane.WARNING_MESSAGE);
            d.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar los datos :( ",
                    "Error al agregar Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void buscar() {
        String nomb, nom, ced, prov, dir, tel, ema;
        int ed;
        boolean proximo = true;
        nomb = JOptionPane.showInputDialog(null, "Digite el nombre del cliente a buscar:");
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("clientes.dat"));
            try {
                while (true) {
                    ced = i.readUTF();
                    nom = i.readUTF();
                    ed = i.readInt();
                    dir = i.readUTF();
                    tel = i.readUTF();
                    ema = i.readUTF();
                    prov = i.readUTF();
                    if (nomb.equals(nom)) {
                        JOptionPane.showMessageDialog(null,
                                "La persona " + nom + " cuya celuda es " + ced
                                + " vive en " + prov + " " + dir + " tiene " + ed + " años. Su telefono es "
                                + tel + " y su email es " + ema);
                        proximo = false;
                    }
                }
            } catch (EOFException e) {
                i.close();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no existe :O ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error del dispositivo :s ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void intercambiar() {
        String nom, ced, prov, dir, tel, ema;
        int ed;
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("auxiliar.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("clientes.dat"));
            try {
                while (true) {
                    ced = i.readUTF();
                    nom = i.readUTF();
                    ed = i.readInt();
                    dir = i.readUTF();
                    tel = i.readUTF();
                    ema = i.readUTF();
                    prov = i.readUTF();
                    o.writeUTF(ced);
                    o.writeUTF(nom);
                    o.writeInt(ed);
                    o.writeUTF(dir);
                    o.writeUTF(tel);
                    o.writeUTF(ema);
                    o.writeUTF(prov);
                }
            } catch (EOFException e) {
                i.close();
                o.close();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no existe :O ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error del dispositivo :s ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editar() {
        String nom, ced, cedu, prov, dir, tel, ema;
        int ed;
        cedu = JOptionPane.showInputDialog(null, "Digite la cedula del cliente a buscar:");
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("clientes.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("auxiliar.dat"));
            try {
                while (true) {
                    ced = i.readUTF();
                    nom = i.readUTF();
                    ed = i.readInt();
                    dir = i.readUTF();
                    tel = i.readUTF();
                    ema = i.readUTF();
                    prov = i.readUTF();
                    if (cedu.equals(ced)) {
                        nom = JOptionPane.showInputDialog("Digite el nombre del cliente: ");
                        ed = Integer.parseInt(JOptionPane.showInputDialog("Digite la edad del cliente: "));
                        dir = JOptionPane.showInputDialog("Digite la direccion del cliente: ");
                        tel = JOptionPane.showInputDialog("Digite el telefono del cliente: ");
                        ema = JOptionPane.showInputDialog("Digite el email del cliente: ");
                        prov = JOptionPane.showInputDialog("Digite la provincia del cliente: ");
                    }
                    o.writeUTF(ced);
                    o.writeUTF(nom);
                    o.writeInt(ed);
                    o.writeUTF(dir);
                    o.writeUTF(tel);
                    o.writeUTF(ema);
                    o.writeUTF(prov);
                }
            } catch (EOFException e) {
                o.close();
                i.close();
                intercambiar();
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo :/ ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error del dispositivo @_@ ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminar() {
        String nom, cedu, ced, prov, dir, tel, ema;
        int ed;
        boolean proximo = true;
        cedu = JOptionPane.showInputDialog(null, "Digite la cedula del cliente a eliminar:");
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("clientes.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("auxiliar.dat"));
            try {
                while (true) {
                    ced = i.readUTF();
                    nom = i.readUTF();
                    ed = i.readInt();
                    dir = i.readUTF();
                    tel = i.readUTF();
                    ema = i.readUTF();
                    prov = i.readUTF();
                    if (!cedu.equals(ced)) {
                        o.writeUTF(ced);
                        o.writeUTF(nom);
                        o.writeInt(ed);
                        o.writeUTF(dir);
                        o.writeUTF(tel);
                        o.writeUTF(ema);
                        o.writeUTF(prov);
                        proximo = false;
                    }
                }                
            } catch (EOFException e) {
                o.close();
                i.close();
                intercambiar();
                JOptionPane.showMessageDialog(null, "Dato eliminado correctamente");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "El archivo no existe -_- ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error del dispositivo :$ ", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
