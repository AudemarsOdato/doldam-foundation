package Login;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UsersAndPass {
        HashMap<String, String> usersAndPass = new HashMap<>();
        HashMap<String, String> usersAndNames = new HashMap<>();

        public UsersAndPass() {
                getInfos();
        }

        public HashMap getLoginInfo() {
                return usersAndPass;
        }

        public String getName(String username) {
                return usersAndNames.get(username);
        }

        public ArrayList<String> getNames() {
                return getData("doctors", "names");
        }

        public ArrayList<String> getUsernames() {
                return getData("doctors", "usernames");
        }

        private ArrayList<String> getData(String personel, String location) {
                ArrayList<String> data = new ArrayList<>();
                try (Scanner scanner = new Scanner(new FileReader("personel" + "\\" + personel + "\\" + location + ".txt"))) {
                        while (scanner.hasNextLine()) {
                                data.add(scanner.nextLine());
                        }
                }
                catch(FileNotFoundException e) {
                        System.out.println(e);
                }

                return data;
        }

        private void getInfos() {
                ArrayList<String> names = getData("doctors", "names");
                ArrayList<String> usernames = getData("doctors", "usernames");
                ArrayList<String> keys = getData("doctors", "keys");

                ArrayList<String> adminUsername = getData("admin", "username");
                ArrayList<String> adminKeys = getData("admin", "key");

                for (int i = 0; i < usernames.size(); i++) {
                        usersAndPass.put(usernames.get(i), keys.get(i));
                        usersAndNames.put(usernames.get(i), names.get(i));
                }

                for (int i = 0; i < adminUsername.size(); i++) {
                        usersAndPass.put(adminUsername.get(i), adminKeys.get(i));
                }
        }
}
