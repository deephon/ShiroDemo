var baseAdv = {
  //列表搜索与显示
  search: function()
  {
    var param = {
      search: {
        "sku" : $("#sku").val(),
        "self_sku" : $("#self_sku").val(),
        "account" : $("#account option:selected").val(),
        "start_date" : $("#start_date").val()
      }
    };
    baseAdv.send(param);
  },
  send: function(param)
  {
    var page = document.getElementById('advInfo');
    page.paging(param);
  }
}