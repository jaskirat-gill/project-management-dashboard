package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Event;
import model.EventLog;
import model.Project;
import org.json.JSONArray;
import org.json.JSONObject;

    // INSPIRED FROM UBC CPSC 210 JSON SERIALIZATION DEMO
    // Represents a reader that reads a single project from JSON data
public class ReadJson {
    private String source;

    // EFFECTS: constructs the reader to read from source file. Initializes source field from parameter
    public ReadJson(String source) {
        this.source = source;
    }

    // EFFECTS: reads project from file and returns it;
    // throws IOException if an error occurs reading data
    public List<Project> read() throws IOException {
        List<Project> projects = new ArrayList<>();
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);

        for (int i = 0; i < jsonArray.length(); i++) {
            projects.add(parseProject(jsonArray.getJSONObject(i)));
        }
        return projects;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses project from JSON object and returns it. Then constructs the project
    private Project parseProject(JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        double target = jsonObject.getDouble("target");
        Project project = new Project(address);
        project.setTargetSale(target);
        addEntries(project, jsonObject);
        return project;
    }

    // MODIFIES: project
    // EFFECTS: parses entries from JSON object and adds them to project
    private void addEntries(Project pr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(pr, nextEntry);
        }
    }

    // MODIFIES: pr
    // EFFECTS: parses entry from JSON object and adds it to project
    private void addEntry(Project pr, JSONObject jsonObject) {
        String payee = jsonObject.getString("payee");
        String paymentType = jsonObject.getString("paymentType");
        Boolean taxPaid = jsonObject.getBoolean("taxPaid");
        String taxType = jsonObject.getString("taxType");
        double amount = jsonObject.getDouble("amount");

        pr.createEntry(payee,paymentType,taxPaid,taxType,amount);
    }
}


