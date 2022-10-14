package ${basePackage}.core;

/**
 * @author ${author}
 * @description 排序对象
 * @date ${date}
 */
public class OrderBy {
   private StringBuilder sb = new StringBuilder();

   public OrderBy add(String column) {
      return add(column, "0");
   }

   public OrderBy add(String column, String ascend) {
      if (this.sb.length() > 0) {
         this.sb.append(", ");
      }
      // 驼峰转下划线
      column = column.replaceAll("[A-Z]", "_$0").toLowerCase();
      this.sb.append(column).append("0".equals(ascend) ? " ASC" : " DESC");

      return this;
   }

   public String toString(){
      return this.sb.toString();
   }
}