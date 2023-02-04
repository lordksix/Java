package earthquake;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilter() {filters = new ArrayList<>();}
    
    public void addFilter(Filter f){filters.add(f);}

    public boolean satisfies(QuakeEntry qe){
        for (Filter f : filters) {if(!f.satisfies(qe)) return false;}
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Filters:\n");
        filters.forEach(f->sb.append(f+"\n"));
        return sb.toString();
    }
    
}
