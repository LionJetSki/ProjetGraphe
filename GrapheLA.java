import java.util.ArrayList;
import java.util.List;

public class GrapheLA extends Graphe{
	private static class Stub {
		public int valuation, cible;
		public Stub (int valuation, int cible) {
			this.valuation = valuation;
			this.cible = cible;
		}
	}
	private	List<Stub> la[];
	
	@SuppressWarnings("unchecked")
	public GrapheLA(int nbNoeuds) {
		super();
		la = new List[nbNoeuds]; // necessite le SuppressWarnings ci-dessus
		for (int i = 0; i < nbNoeuds; ++i)
			la[i] = new ArrayList<>();
	}

	@Override
	public int getNbSommets() {
		return la.length;
	}

	@Override
	public int getValuation(int a, int b) {
		assert estArcOK(a,b);
		List<Stub> stubs = la[a-1];
		for (Stub s : stubs)
			if (s.cible == b)
				return s.valuation;
		return INFINI;
	}

	public void ajouterArc(int a, int v, int b) {
		assert ! aArc(a,b);
		la[a-1].add(new Stub(v, b));
	}
	

	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i< la.length; ++i) {
			str += (i+1) + " =>";
			for (Stub s : la[i]) 
				str += " "+s.cible + "("+s.valuation+")";
			str +="\n";
		}
		return str;
	}
  public int DOut (int sommet ){
    return this.getAdjacent(sommet).size();
  }

  public int DIn (int sommet){
 int degre = 0;
   for(int i = 1; i <= getNbSommets(); ++i ){
     if (getValuation(i,sommet)< INFINI)
       ++ degre;
   } 
    return degre;   
   
  }
    public  ArrayList<Integer> getAdjacent(int  n1){
      ArrayList<Integer> result = new ArrayList<Integer>();
      for (int i = 0 ; (i < la[n1 - 1].size()) ;++i ){
        result.add(la[n1 - 1].get(i).cible);        
      }
      return result;
    }


  
  public boolean estOK(int SDebut, int SFin){
    ArrayList<Integer> atraiter = new ArrayList<Integer>();
    ArrayList<Integer> dejaVerifier = new ArrayList<Integer>();
    boolean test = true;

    if(DIn(SFin) > 0 && DOut(SDebut) > 0){
      atraiter.add(SDebut);
      int noeudTmp;
      
      while(atraiter.size() > 0){
        noeudTmp = atraiter.get(0);
        dejaVerifier.add(atraiter.get(0));
        atraiter.remove(0);
        
        for(int i = 0; i < la[noeudTmp - 1].size() ; ++i){
          if(la[noeudTmp -1].get(i).valuation != INFINI){
          test = true;
            if (la[noeudTmp - 1].get(i).cible == SFin){
              return true;
            }
           for(int s = 0; s < dejaVerifier.size(); ++s){
              if(la[noeudTmp -1].get(i).cible == dejaVerifier.get(s)){
                test = false;
              }
            }
            if(test){
              atraiter.add(la[noeudTmp - 1].get(i).cible);
            }
          }
        }
      }
    }
    throw new IllegalArgumentException("Il n'existe pas de chemin entre " + SDebut + "et " + SFin);
  }
      
}
