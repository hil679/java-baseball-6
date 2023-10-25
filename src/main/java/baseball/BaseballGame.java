package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballGame {
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
        if (isNegative(userInput)) {
            throw new IllegalArgumentException("입력이 음수입니다.");
        }
        if (!isThreeDigits(userInput)) {
            throw new IllegalArgumentException("사용자 입력이 3자리수가 아닙니다.");
        }
        if (isDuplicatedNum(userInput)) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
        if (userInput.contains("0")) {
            throw new IllegalArgumentException("사용자 입력에 0이 포함됐습니다.");
        }
    }

    private boolean isInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean isNegative(String userInput){
        int userInputInt = Integer.parseInt(userInput);
        if (userInputInt < 0) {
            return true;
        }
        return false;
    }

    private static boolean isThreeDigits(String userInput){
        int userInputInt = Integer.parseInt(userInput);
        int userInputLength = (int) ( Math.log10(userInputInt)+1 );
        if (userInputLength == NUMBER_LENGTH) {
            return true;
        }
        return false;
    }

    private static boolean isDuplicatedNum(String userInput) {
        Set<Character> userInputSet = new HashSet<>();

        for (char userInputChar : userInput.toCharArray()) {
            userInputSet.add(userInputChar);
        }

        if (userInputSet.size() == userInput.length()) {
            return false;
        }
        return true;
    }
}
