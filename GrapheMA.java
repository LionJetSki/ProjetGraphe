
import java.util.LinkedList;
import java.util.ArrayList;
public class GrapheMA extends Graphe{
	private int[][] ma;

	public GrapheMA(int nbNoeuds) {
		ma = new int[nbNoeuds][nbNoeuds];
		for (int a = 0; a < nbNoeuds; ++a)
			for (int b = 0; b < nbNoeuds; ++b)
				ma[a][b] = INFINI;
	}
	 //infini valeur 2147483
	@Override
	public int getNbSommets() { return ma.length; }
	
	@Override
	public int getValuation(int a, int b) {
		assert estArcOK(a,b);
		return ma[a-1][b-1];
	}
	
	@Override
	public void ajouterArc(int a, int v, int b) {
		assert ! aArc(a,b);
		ma[a-1][b-1] = v;
	}
	

	@Override
	public String toString() {
		String str = "";
		int v;
		for(int i = 1; i<= getNbSommets(); ++i) {
			str += (i) + " =>";
			for (int j = 1; j <= getNbSommets(); ++j)
				if ((v= getValuation(i,j)) != INFINI) 
					str += " " + j + "(" + v + ")";
			str +="\n";
		}
		return str;
	}
  public int dIn(int NomSommet){
    int degre = 0;
   for(int i = 1; i <= getNbSommets(); ++i ){
     if (getValuation(i,NomSommet)< INFINI)
       ++ degre;
   } 
    return degre;
  }
  public int dOut(int NomSommet){
    int degre = 0;
   for(int i = 1; i <= getNbSommets(); ++i ){
     if (getValuation(NomSommet, i)< INFINI)
       ++ degre;
   } 
    return degre;
  }
  //@Override
  public boolean estOK(int SDebut, int SFin){
    LinkedList<Integer> X= new LinkedList<Integer>();
    LinkedList<Integer> Xverif= new LinkedList<Integer>();

    X.add(SDebut - 1);
    boolean test = true;
    
    if(dIn(SFin) >= 1 && dOut(SDebut) >= 1){
      while(X.size() != 0){
        int noeudTmp = X.get(0);
        Xverif.add(X.get(0));
        X.remove(0);
        for(int i = 0; i < getNbSommets();++i){
          if (ma[noeudTmp][i] != INFINI){
            test = true;
            if (i+1 == SFin){
              return true;
            }
            for(int s = 0; s < Xverif.size(); ++s){
              if(i == Xverif.get(s)){
                test = false;
              }
            }
            if(test){
              X.add(i);
            }
          }
        }
      }
      
    }
    
    throw new IllegalArgumentException("Il n'existe pas de chemin entre " + SDebut + "et " + SFin);
  }
  
  public ArrayList<Integer> getAdjacent(int  n1){
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (int i = 0;i < getNbSommets();++i){
      if(ma[n1 -1][i] != INFINI){
        result.add(i + 1);
      }
    }
    return result;
  }


  
}
