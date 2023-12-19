//teste 3

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto) throws SQLException{
        
        conn = new conectaDAO().connectDB();
        
        try{
            prep = conn.prepareStatement("INSERT INTO `produtos` (`id`, `nome`, `valor`, `status`) values (null,?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            return 1;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Não foi possivel cadastrar o produto, erro: "+ex.getMessage(),null,2);
            return 0;
        }    
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

