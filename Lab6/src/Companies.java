import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Companies {

    private List<Company> companyData;
    private static List<String> keys;

    public Companies(List<Company> data) {
        companyData = data;
    }

    public static void setKeys(List<String> k) {
        keys = k;
    }

    public int length() {
        return companyData.size();
    }

    public List<Map<String, Object>> rawData() {
        List<Map<String, Object>> data = new ArrayList<>();
        companyData.forEach(company->data.add(company.rawData()));
        return data;
    }

    public Companies process(String request, Logger logger) throws Exception {
        Scanner sc = new Scanner(request).useDelimiter(", ");
        String key;
        if (sc.hasNext()) {
            key = sc.next();
        }
        else {
            throw new Exception("Invalid request.");
        }
        Companies comp;
        if (sc.hasNext()) {
            switch (key) {
                case "shortTitle": {
                    comp = filterShortTitle(sc.next());
                    break;
                }
                case "branch": {
                    comp = filterBranch(sc.next());
                    break;
                }
                case "activity": {
                    comp = filterActivity(sc.next());
                    break;
                }
                case "dateFoundation": {
                    String from = sc.next();
                    String to;
                    if (sc.hasNext()) {
                        to = sc.next();
                    }
                    else {
                        to = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
                    }
                    comp = filterDateFoundation(from, to);
                    break;
                }
                case "employeeCount": {
                    int from = sc.nextInt();
                    int to;
                    if (sc.hasNext()) {
                        to = sc.nextInt();
                    }
                    else {
                        to = Integer.MAX_VALUE;
                    }
                    comp = filterEmployeeCount(from, to);
                }
                default: {
                    comp = null;
                }
            }
        }
        else {
            throw new Exception("No request value found");
        }
        logger.logRequest(request, comp.length());
        return comp;
    }

    private Companies filterShortTitle(String shortTitle) {
        return new Companies(
            companyData
                .stream()
                .filter(company->company.shortTitleEquals(shortTitle))
                .collect(Collectors.toList())
        );
    }

    private Companies filterBranch(String branch) {
        return new Companies(
                companyData
                        .stream()
                        .filter(company->company.branchEquals(branch))
                        .collect(Collectors.toList())
        );
    }

    private Companies filterActivity(String activity) {
        return new Companies(
                companyData
                        .stream()
                        .filter(company->company.activityEquals(activity))
                        .collect(Collectors.toList())
        );
    }

    private Companies filterDateFoundation(String from, String to) {
        return new Companies(
                companyData
                        .stream()
                        .filter(company->company.dateBetween(from, to))
                        .collect(Collectors.toList())
        );
    }

    private Companies filterEmployeeCount(int from, int to) {
        return new Companies(
            companyData
                    .stream()
                    .filter(company->company.employeeNumberBetween(from, to))
                    .collect(Collectors.toList())
    );}
}
