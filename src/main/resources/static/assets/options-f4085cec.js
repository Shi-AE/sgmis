import{_ as D,G as I,z as U,N as o,d as p,M as O,r,o as C,c as V,f as t,t as a,C as c,b as k,q as K}from"./index-87c3fdd7.js";const N={props:{api:{type:String,required:!0},head:{type:String,required:!0}},data(){return{columns:[{title:"序号",dataIndex:"index"},{title:this.$props.head,dataIndex:"name",align:"left",filterable:{filter:(l,e)=>e.name.includes(l),slotName:"head-filter",icon:()=>I(U)}},{title:"来源",dataIndex:"author",align:"center"},{title:"操作",slotName:"operation",align:"right"}],data:[],rowSelection:{type:"checkbox",showCheckedAll:!0},selectedKeys:[],inputModal:!1,batchDeleteModal:!1,updateModal:!1,form:{name:"",id:null,gid:null}}},methods:{resetForm(){this.form={name:"",id:null,gid:null}},handleInputModal(){this.inputModal=!0},handleBatchDeleteModal(){this.batchDeleteModal=!0},handleUpdateModal(l){if(l.gid===0){o.error("无法修改系统选项");return}this.form.name=l.name,this.form.id=l.id,this.form.gid=l.gid,this.updateModal=!0},handleCancel(){this.$store.state.isPending?o.error("请求已发送，取消失败"):o.info("已取消"),this.resetForm()},async handleInput(){let l=this.form.name;if(l==="")return o.error("提交信息为空"),!0;if(this.data.some(e=>e.name===l))return o.error("提交重复信息"),!0;await p({method:"POST",url:`xxpz/${this.$props.api}`,data:{name:this.form.name},message:"正在处理添加请求"}).then(e=>(e.data.code===200?(o.success(e.data.message),this.data.unshift({...e.data.data,key:e.data.data.id,index:this.data.length+1,disabled:e.data.data.gid===0})):o.error(e.data.message),this.resetForm(),!0)).catch(()=>{this.inputModal=!1})},handleDelete(l){if(l.gid===0){o.error("无法删除系统选项");return}const e=O.warning({title:"删除配置选项",content:`确认要删除"${l.name}"吗？`,closable:!0,hideCancel:!1,onCancel:()=>{this.$store.state.isPending?o.error("请求已发送，取消失败"):o.info("已取消")},onBeforeOk:()=>{p({method:"DELETE",url:`xxpz/${l.id}`,message:"正在处理删除请求"}).then(u=>{u.data.code===200?(o.success(u.data.message),this.data=this.data.filter(i=>i.id!==l.id)):o.error(u.data.message),e.close()}).catch(()=>{e.close()})}})},async handleBatchDelete(){if(!this.selectedKeys.length)return o.error("未选中信息"),!0;let l=Object.values(this.selectedKeys);await p({method:"POST",url:"xxpz/delete",data:l,message:"正在批量删除"}).then(e=>(e.data.code===200?(o.success(e.data.message),this.data=this.data.filter(u=>!l.includes(u.id))):o.error(e.data.message),!0)).catch(()=>{this.inputModal=!1})},async handleUpdate(){await p({method:"PUT",url:"xxpz",data:this.form,message:"正在更新数据"}).then(l=>(l.data.code===200?(o.success(l.data.message),this.data=this.data.map(e=>(e.id===this.form.id&&(e.name=this.form.name),e))):o.error(l.data.message),this.resetForm(),!0)).catch(()=>{this.updateModal=!1})}},mounted(){p({method:"GET",url:`xxpz/${this.$props.api}`,message:"查询配置信息"}).then(l=>{l.data.code===200?this.data=l.data.data.map((e,u)=>({...e,key:e.id,index:u+1,disabled:e.gid===0})):o.error(l.data.message)})}},S={class:"button-group"},T={class:"custom-filter"},E={class:"custom-filter-footer"};function P(l,e,u,i,n,s){const h=r("a-divider"),f=r("a-button"),_=r("a-space"),y=r("a-input"),M=r("a-table"),v=r("a-form-item"),x=r("a-form"),g=r("a-modal");return C(),V("div",null,[t(h,{size:2,style:{"border-bottom-style":"dotted"},orientation:"left"},{default:a(()=>[c("操作")]),_:1}),k("div",S,[t(_,null,{split:a(()=>[t(h,{direction:"vertical"})]),default:a(()=>[t(f,{type:"primary",status:"success",onClick:e[0]||(e[0]=d=>s.handleInputModal())},{default:a(()=>[c("添加")]),_:1}),t(f,{type:"primary",status:"danger",onClick:e[1]||(e[1]=d=>s.handleBatchDeleteModal())},{default:a(()=>[c("批量删除")]),_:1})]),_:1})]),t(h,{size:2,style:{"border-bottom-style":"dotted"},orientation:"left"},{default:a(()=>[c("基础选项")]),_:1}),t(M,{columns:n.columns,data:n.data,scroll:{minWidth:540},pagination:!1,"row-selection":n.rowSelection,selectedKeys:n.selectedKeys,"onUpdate:selectedKeys":e[2]||(e[2]=d=>n.selectedKeys=d),"filter-icon-align-left":!0},{"head-filter":a(({filterValue:d,setFilterValue:b,handleFilterConfirm:w,handleFilterReset:z})=>[k("div",T,[t(_,{direction:"vertical"},{default:a(()=>[t(y,{"model-value":d[0],onInput:B=>b([B])},null,8,["model-value","onInput"]),k("div",E,[t(f,{onClick:w},{default:a(()=>[c("查找")]),_:2},1032,["onClick"]),t(f,{onClick:z},{default:a(()=>[c("重置")]),_:2},1032,["onClick"])])]),_:2},1024)])]),operation:a(({record:d})=>[t(_,null,{default:a(()=>[t(f,{type:"primary",onClick:b=>s.handleUpdateModal(d)},{default:a(()=>[c("编辑")]),_:2},1032,["onClick"]),t(f,{type:"primary",status:"danger",onClick:b=>s.handleDelete(d)},{default:a(()=>[c("删除")]),_:2},1032,["onClick"])]),_:2},1024)]),_:1},8,["columns","data","row-selection","selectedKeys"]),t(g,{visible:n.inputModal,"onUpdate:visible":e[4]||(e[4]=d=>n.inputModal=d),width:"calc(300px + 0.1 * 100vw)",title:"添加配置选项",onBeforeOk:s.handleInput,onCancel:s.handleCancel},{default:a(()=>[t(x,{model:n.form},{default:a(()=>[t(v,{field:"name",label:"选项名称"},{default:a(()=>[t(y,{modelValue:n.form.name,"onUpdate:modelValue":e[3]||(e[3]=d=>n.form.name=d),modelModifiers:{lazy:!0}},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["visible","onBeforeOk","onCancel"]),t(g,{visible:n.updateModal,"onUpdate:visible":e[6]||(e[6]=d=>n.updateModal=d),width:"calc(300px + 0.1 * 100vw)",title:"编辑配置选项",onBeforeOk:s.handleUpdate,onCancel:s.handleCancel},{default:a(()=>[t(x,{model:n.form},{default:a(()=>[t(v,{field:"name",label:"选项名称"},{default:a(()=>[t(y,{modelValue:n.form.name,"onUpdate:modelValue":e[5]||(e[5]=d=>n.form.name=d),modelModifiers:{lazy:!0}},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])]),_:1},8,["visible","onBeforeOk","onCancel"]),t(g,{visible:n.batchDeleteModal,"onUpdate:visible":e[7]||(e[7]=d=>n.batchDeleteModal=d),width:"calc(300px + 0.1 * 100vw)",title:"批量删除配置选项",onBeforeOk:s.handleBatchDelete,onCancel:s.handleCancel,simple:""},{default:a(()=>[c(" 确定要删除选中的配置选项吗？ ")]),_:1},8,["visible","onBeforeOk","onCancel"])])}const m=D(N,[["render",P],["__scopeId","data-v-55297885"]]),q={__name:"options",setup(l){return(e,u)=>{const i=r("a-tab-pane"),n=r("a-tabs");return C(),K(n,{"default-active-key":"1","lazy-load":""},{default:a(()=>[t(i,{key:"1",title:"羽色配置"},{default:a(()=>[t(m,{api:"yspz",head:"羽色"})]),_:1}),t(i,{key:"2",title:"类型配置"},{default:a(()=>[t(m,{api:"lxpz",head:"类型"})]),_:1}),t(i,{key:"3",title:"眼砂配置"},{default:a(()=>[t(m,{api:"yanpz",head:"眼砂"})]),_:1}),t(i,{key:"4",title:"级别配置"},{default:a(()=>[t(m,{api:"jbpz",head:"级别"})]),_:1}),t(i,{key:"5",title:"医疗护理"},{default:a(()=>[t(m,{api:"ylhl",head:"用途名称"})]),_:1}),t(i,{key:"6",title:"鸽蛋雏鸽状态"},{default:a(()=>[t(m,{api:"gdcgzt",head:"状态"})]),_:1}),t(i,{key:"7",title:"作育者"},{default:a(()=>[t(m,{api:"breeder",head:"姓名"})]),_:1}),t(i,{key:"8",title:"鸽子状态"},{default:a(()=>[t(m,{api:"state",head:"鸽子状态"})]),_:1})]),_:1})}}};export{q as default};
