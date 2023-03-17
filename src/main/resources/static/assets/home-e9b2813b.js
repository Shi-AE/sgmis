import {
    _ as R,
    a as E,
    b as _,
    c as B,
    e as t,
    g as b,
    h as x,
    i as g,
    I as q,
    j as H,
    k as j,
    l as P,
    m as y,
    M as Z,
    n as V,
    o as p,
    p as A,
    q as n,
    r as a,
    t as K,
    u as O,
    x as W,
    y as o
} from "./index-425b9ad0.js";

const F=b({name:"IconHome",props:{size:{type:[Number,String]},strokeWidth:{type:Number,default:4},strokeLinecap:{type:String,default:"butt",validator: e=>["butt","round","square"].includes(e)},strokeLinejoin:{type:String,default:"miter",validator: e=>["arcs","bevel","miter","miter-clip","round"].includes(e)},rotate:Number,spin:Boolean},emits:{click: e=>!0},setup(e, {emit:c}){const s=x("icon"),i=g(()=>[s,`${s}-home`,{[`${s}-spin`]:e.spin}]),m=g(()=>{const u={};return e.size&&(u.fontSize=j(e.size)?`${e.size}px`:e.size),e.rotate&&(u.transform=`rotate(${e.rotate}deg)`),u});return{cls:i,innerStyle:m,onClick: u=>{c("click",u)}}}}),T=["stroke-width","stroke-linecap","stroke-linejoin"],Y=_("path",{d:"M7 17 24 7l17 10v24H7V17Z"},null,-1),D=_("path",{d:"M20 28h8v13h-8V28Z"},null,-1),G=[Y,D];function J(e, c, s, i, m, h){return p(),B("svg",{viewBox:"0 0 48 48",fill:"none",xmlns:"http://www.w3.org/2000/svg",stroke:"currentColor",class:V(e.cls),style:P(e.innerStyle),"stroke-width":e.strokeWidth,"stroke-linecap":e.strokeLinecap,"stroke-linejoin":e.strokeLinejoin,onClick:c[0]||(c[0]=(...u)=>e.onClick&&e.onClick(...u))},G,14,T)}var C=H(F,[["render",J]]);const Q=Object.assign(C,{install:(e, c)=>{var s;const i=(s=c==null?void 0:c.iconPrefix)!=null?s:"";e.component(i+C.name,C)}});const U=b({components:{IconCaretRight:q,IconCaretLeft:K,IconHome:Q,IconCalendar:O},setup(){const e=W(!1);return{collapsed:e,onCollapse:()=>{e.value=!e.value},onClickMenuItem(s){Z.info({content:`You select ${s}`,showIcon:!0})}}}}),X= e=>(A("data-v-6dd31e44"),e=e(),E(),e),ee=X(()=>_("div",{class:"logo"},null,-1));function te(e, c, s, i, m, h){const u=a("IconHome"),l=a("a-menu-item"),r=a("IconCalendar"),d=a("a-sub-menu"),v=a("a-menu"),I=a("a-layout-sider"),M=a("IconCaretRight"),$=a("IconCaretLeft"),w=a("a-button"),N=a("a-layout-header"),f=a("a-breadcrumb-item"),S=a("a-breadcrumb"),z=a("a-layout-content"),L=a("a-layout-footer"),k=a("a-layout");return p(),y(k,{class:"layout-demo"},{default:n(()=>[t(I,{"hide-trigger":"",collapsible:"",collapsed:e.collapsed,breakpoint:"lg",width:220,onCollapse:e.onCollapse},{default:n(()=>[ee,t(v,{defaultOpenKeys:["1"],defaultSelectedKeys:["0_3"],style:{width:"100%"},onMenuItemClick:e.onClickMenuItem},{default:n(()=>[t(l,{key:"0_1",disabled:""},{default:n(()=>[t(u),o(" Menu 1 ")]),_:1}),t(l,{key:"0_2"},{default:n(()=>[t(r),o(" Menu 2 ")]),_:1}),t(l,{key:"0_3"},{default:n(()=>[t(r),o(" Menu 3 ")]),_:1}),t(d,{key:"1"},{title:n(()=>[_("span",null,[t(r),o("Navigation 1 ")])]),default:n(()=>[t(l,{key:"1_1"},{default:n(()=>[o("Menu 1")]),_:1}),t(l,{key:"1_2"},{default:n(()=>[o("Menu 2")]),_:1}),t(d,{key:"2",title:"Navigation 2"},{default:n(()=>[t(l,{key:"2_1"},{default:n(()=>[o("Menu 1")]),_:1}),t(l,{key:"2_2"},{default:n(()=>[o("Menu 2")]),_:1})]),_:1}),t(d,{key:"3",title:"Navigation 3"},{default:n(()=>[t(l,{key:"3_1"},{default:n(()=>[o("Menu 1")]),_:1}),t(l,{key:"3_2"},{default:n(()=>[o("Menu 2")]),_:1}),t(l,{key:"3_3"},{default:n(()=>[o("Menu 3")]),_:1})]),_:1})]),_:1}),t(d,{key:"4"},{title:n(()=>[_("span",null,[t(r),o("Navigation 4 ")])]),default:n(()=>[t(l,{key:"4_1"},{default:n(()=>[o("Menu 1")]),_:1}),t(l,{key:"4_2"},{default:n(()=>[o("Menu 2")]),_:1}),t(l,{key:"4_3"},{default:n(()=>[o("Menu 3")]),_:1})]),_:1})]),_:1},8,["onMenuItemClick"])]),_:1},8,["collapsed","onCollapse"]),t(k,null,{default:n(()=>[t(N,{style:{"padding-left":"20px"}},{default:n(()=>[t(w,{shape:"round",onClick:e.onCollapse},{default:n(()=>[e.collapsed?(p(),y(M,{key:0})):(p(),y($,{key:1}))]),_:1},8,["onClick"])]),_:1}),t(k,{style:{padding:"0 24px"}},{default:n(()=>[t(S,{style:{margin:"16px 0"}},{default:n(()=>[t(f,null,{default:n(()=>[o("Home")]),_:1}),t(f,null,{default:n(()=>[o("List")]),_:1}),t(f,null,{default:n(()=>[o("App")]),_:1})]),_:1}),t(z,null,{default:n(()=>[o("Content")]),_:1}),t(L,null,{default:n(()=>[o("Footer")]),_:1})]),_:1})]),_:1})]),_:1})}const oe=R(U,[["render",te],["__scopeId","data-v-6dd31e44"]]);export{oe as default};
