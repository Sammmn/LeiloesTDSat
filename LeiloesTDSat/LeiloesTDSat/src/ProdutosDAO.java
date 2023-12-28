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
    
    public int cadastrarProduto (ProdutosDTO produto) throws SQLException, ClassNotFoundException{
        
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
    
    public ArrayList<ProdutosDTO> listarProdutos() throws ClassNotFoundException{
        
        conn = new conectaDAO().connectDB();
        ArrayList<ProdutosDTO> produtos = new ArrayList();
        
        try{
            prep = conn.prepareStatement("SELECT * FROM produtos");
            
            ResultSet rs = prep.executeQuery();
            
            while(rs.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                produtos.add(p);
            }
            return produtos;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Não foi possivel recuperar as informações dos produtos, erro: "+ex.getMessage(),null,2);
            return null;
        }  
    }
    
    public void venderProduto(int produtoid) throws ClassNotFoundException{
        
        conn = new conectaDAO().connectDB();
        
        try{
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            prep.setInt(1, produtoid);
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda realizada!",null,2);
  
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a venda, erro: "+ex.getMessage(),null,2);
        }
    }            
}

