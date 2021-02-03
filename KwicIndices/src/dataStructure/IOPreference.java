package dataStructure;

import resources.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class IOPreference {
    private static IOPreference singleInstance = null;
    public List<Preference> preferenceList;
    public Map<String,String> casePreferenceList;
    public  class Preference{
        public String iD;
        public String displayName;
        public List<String> attributes;
    }
    public enum IOMethods {
        INPUT,
        OUTPUT;

    }
    private IOPreference() {
        String jsonString = Constants.IOOPTION;
        Gson gson = new Gson();
        Type type = new TypeToken<List<Preference>>(){}.getType();
        preferenceList = gson.fromJson(jsonString, type);
        //System.out.println(String.join(",",preferenceMap.values()););
       // preferenceList.forEach(k-> System.out.println("Press - '"+k.iD+"', For - "+k.displayName));
        jsonString = Constants.CASEOPTION;
        type = new TypeToken<Map<String,String>>(){}.getType();
        casePreferenceList = gson.fromJson(jsonString, type);;
    }

    public static IOPreference getInstance() {
        if (singleInstance == null)
            singleInstance = new IOPreference();

        return singleInstance;
    }
    final Hashtable<IOMethods, Map<String, String>> myIOPreference = new Hashtable<>();

    private void setIOPreference(IOMethods aMethod, Map<String, String> aData) {
       if(aData!=null) {
           if (myIOPreference.containsKey(aMethod)) {
               myIOPreference.replace(aMethod, aData);
           } else {
               myIOPreference.put(aMethod, aData);
           }
       }

    }
    public String getIOPreference(IOMethods aMethod) {
        Map<String, String>  data = myIOPreference.getOrDefault(aMethod,null);
        if (data == null) {
            return Constants.IOOPTION_DEF;
        }
        else
        {
            return data.get(Constants.IOPREFERENCE_KEY);
        }
    }

    public Map<String, String> getIOPreferenceData(IOMethods aMethod, String[] aKeys) {
      //  System.out.println(String.join(",", aKeys));
        Map<String, String> data = myIOPreference.getOrDefault(aMethod, null);
        if (data == null) {
            return null;
        } else {
            Map<String, String> result = data.entrySet()
                    .stream()
                    .filter(map -> Arrays.stream(aKeys).anyMatch(map.getKey()::equals))
                    .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
            return result;
        }
    }

    public void acceptIOPreference(IOMethods aMethod) {
        Scanner sc;
        boolean inputOk = false;
        String ioPreference = Constants.IOOPTION_DEF;
            Preference preference = null;
        Map<String, String> aData = new HashMap<>();
        while (!inputOk) {
            sc = new Scanner(System.in);
            System.out.println("Please provide "+aMethod+" preference: Choose the option from below list.");
            preferenceList.forEach((k)-> System.out.println("Keyin - '"+k.iD+"', For - "+k.displayName));
            ioPreference = sc.next().substring(0,1);
            String finalIoPreference = ioPreference;
            if (!preferenceList.stream().anyMatch(O->O.iD.equalsIgnoreCase(finalIoPreference))) {
                System.out.println("Not valid input preference, try again.");
                sc.reset();
            } else {
                inputOk = true;
            }
            aData.put(Constants.IOPREFERENCE_KEY,finalIoPreference.toUpperCase());
            preference = preferenceList.stream()
                    .filter(p -> p.iD.equalsIgnoreCase(finalIoPreference))
                    .findAny()
                    .orElse(null);
        }

            Preference finalPreference = preference;
            preference.attributes.stream().forEach(s -> {
                String attr = "";
                Scanner scAttr = new Scanner(System.in);
                System.out.println("Provide detail for IO method -" + aMethod + ", IO Option - " + finalPreference.displayName +", Attribute - "+s);
                attr = scAttr.nextLine();
                aData.put(s,attr);
                scAttr.reset();
            });
        if (aMethod==IOMethods.INPUT) {
            sc = new Scanner(System.in);
            System.out.println("Do you prefer non case sensitive execution? - It impacts sort order and shifts ignore word.");
            System.out.println("select appropriate option");
            casePreferenceList.entrySet().forEach(entry->{
                System.out.println("Keyin - '"+entry.getKey() + "', For - " + entry.getValue());
            });
            String input = sc.next().substring(0,1).toUpperCase();

            if (casePreferenceList.containsKey(input.toUpperCase()))
                aData.put(Constants.CASEPREFERENCE_KEY, casePreferenceList.get(input));

        }
        setIOPreference(aMethod, aData);
    }
}
