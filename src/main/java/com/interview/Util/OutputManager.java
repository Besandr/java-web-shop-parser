package com.interview.Util;

import com.interview.model.Offer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputManager {

    String path;

    public OutputManager(String path) {
        this.path = "d:\\result.xml" ;
    }

    public static void write (List<Offer> offers, String path){
        try(FileWriter writer = new FileWriter(new File(path))){
            for (Offer offer : offers) {
                if (offer.getSizes().size() != 0) {
                    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                            "<offers>\n");
                    for (String size : offer.getSizes()) {
                        writer.write(" <offer>\n");
                        writer.write("  <name>" + offer.getName() + "</name>" + "\n");
                        writer.write("  <brand>" + offer.getBrand() + "</brand>" + "\n");
                        writer.write("  <color>" + offer.getColor() + "</color>" + "\n");
                        writer.write("  <size>" + size + "</size>" + "\n");
                        writer.write("  <price>" + offer.getPrice() + "</price>" + "\n");
                        if (offer.getInitialPrice() != null) {
                            writer.write("  <initial price>" + offer.getInitialPrice() + "</initial price>" + "\n");
                        }
                        writer.write("  <description>" + offer.getDescription() + "</description>" + "\n");
                        writer.write("  <articleID>" + offer.getArticleID() + "</articleID>" + "\n");
                        writer.write("  <shipping cost>" + offer.getShippingCost() + "</shipping cost>" + "\n");
                        writer.write(" </offer>\n");
                    }
                } else {
                    writer.write(" <offer>\n");
                    writer.write("  <name>" + offer.getName() + "</name>" + "\n");
                    writer.write("  <brand>" + offer.getBrand() + "</brand>" + "\n");
                    writer.write("  <color>" + offer.getColor() + "</color>" + "\n");
                    writer.write("  <price>" + offer.getPrice() + "</price>" + "\n");
                    if (offer.getInitialPrice() != null) {
                        writer.write("<  initial price>" + offer.getInitialPrice() + "</initial price>" + "\n");
                    }
                    writer.write("  <description>" + offer.getDescription() + "</description>" + "\n");
                    writer.write("<  articleID>" + offer.getArticleID() + "</articleID>" + "\n");
                    writer.write("<  shipping cost>" + offer.getShippingCost() + "</shipping cost>" + "\n");
                    writer.write(" </offer>\n");
                }
            }
            writer.write("</offers>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
