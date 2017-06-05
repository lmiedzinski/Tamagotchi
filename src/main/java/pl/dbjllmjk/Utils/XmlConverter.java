package pl.dbjllmjk.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import pl.dbjllmjk.Model.Pet;

/**
 * XmlConverter provides importing and exporting {@link Pet} objects to/from XML
 * files.
 */
public class XmlConverter {

    /**
     * Provides serializing {@link Pet} class to XML format.
     *
     * @param p Instance of {@link Pet} class.
     * @param path of a file to save.
     */
    public static void serializePet(Pet p, String path) {
        XStream writer = new XStream();
        writer.alias("pet", Pet.class);
        writer.omitField(Pet.class, "actions");
        String xmlContent = writer.toXML(p);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)))) {
            bw.write(xmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows to deserialize object of {@link Pet} class from XML file.
     *
     * @param fileName Name or path of a file containing serialized {@link Pet}
     * object.
     * @return Instance of {@link Pet} class.
     */
    public static Pet deserializePet(String fileName) {
        XStream reader = new XStream();
        reader.alias("pet", Pet.class);
        Pet p = null;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            p = (Pet) reader.fromXML(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
