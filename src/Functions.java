import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Functions {
    HashMap<String, Integer> debtorsList;
    HashMap<String, Integer> debtorsFinalList;
    HashMap<String, Integer> count;

    public Functions() {
        this.debtorsList = new HashMap<>();
        this.debtorsFinalList = new HashMap<>();
        this.count = new HashMap<>();
    }

    public HashMap<String, Integer> getDebtorsList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String[] main = list.get(i).split(";");
            String[] next = main[2].split(",");
            for (int k = 0; k < next.length; k++) {
                if (debtorsList.containsKey(main[0] + "," + next[k])) {
                    debtorsList.put(main[0] + "," + next[k], debtorsList.get(main[0] + "," + next[k]) + Integer.parseInt(main[1]) / (next.length + 1));
                } else {
                    debtorsList.put(main[0] + "," + next[k], Integer.parseInt(main[1]) / (next.length + 1));
                }
            }
        }
        return debtorsList;
    }

    public HashMap<String, Integer> getPairsDebtors() {
        for (Map.Entry<String, Integer> entry1 : debtorsList.entrySet()) {
            for (Map.Entry<String, Integer> entry2 : debtorsList.entrySet()) {
                String[] pair1 = entry1.getKey().split(",");
                String[] pair2 = entry2.getKey().split(",");
                if (pair1[0].equals(pair2[1]) && pair1[1].equals(pair2[0])) {
                    if (entry1.getValue() > entry2.getValue()) {
                        int newEntry = entry1.getValue() - entry2.getValue();
                        debtorsList.put(entry1.getKey(), newEntry);
                        debtorsList.put(entry2.getKey(), 0);
                        debtorsFinalList.put(pair2[0] + "," + pair1[0], newEntry);
                    } else if (entry1.getValue() < entry2.getValue()) {
                        int newEntry1 = entry2.getValue() - entry1.getValue();
                        debtorsList.put(entry2.getKey(), newEntry1);
                        debtorsList.put(entry1.getKey(), 0);
                        debtorsFinalList.put(pair1[0] + "," + pair2[0], newEntry1);
                    }
                }
            }
        }
        for (Map.Entry<String, Integer> entryDebtorsFinalList : debtorsFinalList.entrySet()) {
            for (Map.Entry<String, Integer> entryDebtorsList : debtorsList.entrySet()) {
                String[] pairDebtorsFinalList = entryDebtorsFinalList.getKey().split(",");
                String[] pairDebtorsList = entryDebtorsList.getKey().split(",");
                if (pairDebtorsFinalList[0].equals(pairDebtorsList[1]) && pairDebtorsFinalList[1].equals(pairDebtorsList[0]) || pairDebtorsFinalList[0].equals(pairDebtorsList[0]) && pairDebtorsFinalList[1].equals(pairDebtorsList[1])) {
                    debtorsFinalList.remove(entryDebtorsFinalList.getKey());
                }
            }
        }
        return debtorsList;
    }

    public HashMap<String, Integer> getDebtorsFinalList() {
        return debtorsFinalList;
    }

    public HashMap<String, Integer> getCount(String answer) {
        String[] firstSplit = answer.split(";");
        String[] secondSplit = firstSplit[2].split(",");
        if (!count.isEmpty()) {
            for (int k = 0; k < secondSplit.length; k++) {
                for (Map.Entry<String, Integer> entry : count.entrySet()) {
                    String[] pair = entry.getKey().split(",");
                    if (pair[0].equals(secondSplit[k]) && pair[1].equals(firstSplit[0])) {
                        if (entry.getValue() > Integer.parseInt(firstSplit[1])) {
                            int newEntry = entry.getValue() - Integer.parseInt(firstSplit[1]);
                            count.put(entry.getKey(), newEntry);
                        } else if (entry.getValue() < Integer.parseInt(firstSplit[1])) {
                            int newEntry1 = Integer.parseInt(firstSplit[1]) - entry.getValue();
                            count.put(firstSplit[0] + "," + secondSplit[k], newEntry1);
                            count.remove(entry.getKey());
                        }
                    }
                }
            }
        } else {
            for (int k = 0; k < secondSplit.length; k++) {
                count.put(firstSplit[0] + "," + secondSplit[k], Integer.parseInt(firstSplit[1]) / (secondSplit.length + 1));
            }
        }
        return count;
    }
}
