class Trie {
    Trie [] children;
    boolean isSingleWord;
    boolean canContinue;
    boolean history;
    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isSingleWord = true;
        canContinue = false;
        history = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word.isEmpty()){   
            isSingleWord = true;
            history = true;
            return;
        }
        canContinue = true;
        if(!history){
            isSingleWord = false;
        }
        int current = word.charAt(0)-97;
        if(children[current]==null){
            children[current] = new Trie();
        }
        children[current].insert(word.substring(1));
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word.isEmpty()&&isSingleWord){
            return true;
        }
        if(word.isEmpty()&&!isSingleWord){
            return false;
        }
        int current = word.charAt(0)-97;
        if(children[current]==null&&!word.isEmpty()){
            return false;
        }
        if(children[current]==null&&isSingleWord){
            return false;
        }
        return children[current].search(word.substring(1));
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix.isEmpty()&&(canContinue||isSingleWord)){
            return true;
        }
        int current = prefix.charAt(0)-97;
        if(children[current]==null||!canContinue){
            return false;
        }
        return children[current].startsWith(prefix.substring(1));
    }
}
