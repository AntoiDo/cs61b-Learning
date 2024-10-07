
public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     *
     * @param word
     * @return true表示为回文
     */
    public boolean isPalindrome(String word) {
        if(word.length() == 1 || word.isEmpty()){
            return true;
        }
        Deque deque = wordToDeque(word);
        while(deque.size() > 1){
            if(deque.removeFirst() != deque.removeLast()){
                return false;
            }
        }
        // 如果完全匹配则是回文
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        int middle = len / 2;
        for (int i = 0; i < middle; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
