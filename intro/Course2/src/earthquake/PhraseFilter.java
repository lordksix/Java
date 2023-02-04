package earthquake;

public class PhraseFilter implements Filter{
    private String where;
    private String phrase;

    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
    }
    public boolean satisfies(QuakeEntry qe){
        switch(where){
            case "start":
            if(qe.getInfo().startsWith(phrase)) return true;
            break;
            case "end":
            if(qe.getInfo().endsWith(phrase)) return true;
            break;
            case "any":
            if(qe.getInfo().contains(phrase)) return true;
            break;
            default:
            break;
        }
        return false;
    }
    @Override
    public String toString() {
        return "PhraseFilter [where=" + where + ", phrase=" + phrase + "]";
    }
    
}
