package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.*;

public class BaseballGame {
    private final int NUMBER_LENGTH = 3;
    public void run(){
        printGameStart();
        List<Integer> randomNum = initRandomNum();
        List<Integer> userNum = inputUserNum();
    }

    private void printGameStart(){
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    private List<Integer> initRandomNum(){
        List<Integer> randomNumList = new ArrayList<>();
        
        while (randomNumList.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!randomNumList.contains(randomNumber)) {
                randomNumList.add(randomNumber);
            }
        }

        return randomNumList;
    }
    
    private List<Integer> inputUserNum(){
        System.out.print("숫자를 입력해주세요 : ");
        String userInputString = Console.readLine();
        checkUserNumForm(userInputString);

        Integer userInput = Integer.valueOf(userInputString);
        List<Integer> userNum = new ArrayList<>();

        while(userInput != 0){
            userNum.add(0, userInput % 10);
            userInput /= 10;
        }

        return userNum;
    }

    private void checkUserNumForm(String userInput) {
        if(!isInteger(userInput)){
            throw new IllegalArgumentException("입력이 숫자가 아닙니다.");
        }
        if (userInput.length() != 3) {
            throw new IllegalArgumentException("사용자 입력이 3자리수가 아닙니다.");
        }
        if (isDuplicatedNum(userInput)) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
        if (userInput.contains("0")) {
            throw new IllegalArgumentException("사용자 입력에 0이 포함됐습니다.");
        }
    }

    private boolean isDuplicatedNum(String userInput){
        Set<Character> userInputSet = new HashSet<>();

        for(char userInputChar : userInput.toCharArray()){
            userInputSet.add(userInputChar);
        }

        if(userInputSet.size() == userInput.length()){
            return false;
        }
        return true;
    }

    private boolean isInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private Map<String, Integer> countStrikeAndBall(List<Integer> userNum, List<Integer> randomNum){
        Map<String, Integer> resultList = new HashMap<>();
        initStrikeAndBallNum(resultList);

        for(int i = 0; i < NUMBER_LENGTH; i++){
            if(userNum.get(i) == randomNum.get(i)){
                resultList.put("strike", resultList.get("strike") + 1);
            } else if(randomNum.contains(userNum.get(i))){
                resultList.put("ball", resultList.get("strike") + 1);
            }
        }
        return resultList;
    }

    private void initStrikeAndBallNum(Map<String, Integer> resultList){
        resultList.put("strike", 0);
        resultList.put("ball", 0);
    }

}
