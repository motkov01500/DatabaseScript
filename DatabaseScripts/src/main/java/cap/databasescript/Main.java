package cap.databasescript;

public class Main {

    public static void main(String[] args) {
        ScriptService scriptService = ScriptService.getINSTANCE();

        scriptService.getAllCompanies().forEach(company->
                System.out.println(company.getName()));

    }
}