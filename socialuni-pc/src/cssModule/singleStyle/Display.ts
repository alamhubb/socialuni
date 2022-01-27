import CssModule from "@/cssModule/singleStyle/CssModule";

export default class Display implements CssModule{
static get none(){
      return {
        display: 'none'
      }
    }
static get flex(){
      return {
        display: 'flex'
      }
    }
static get block(){
      return {
        display: 'block'
      }
    }
static get inline(){
      return {
        display: 'inline'
      }
    }
static get inlineBlock(){
      return {
        display: 'inlineBlock'
      }
    }
static get listItem(){
      return {
        display: 'listItem'
      }
    }
static get runIn(){
      return {
        display: 'runIn'
      }
    }
static get compact(){
      return {
        display: 'compact'
      }
    }
static get marker(){
      return {
        display: 'marker'
      }
    }
static get table(){
      return {
        display: 'table'
      }
    }
static get inlineTable(){
      return {
        display: 'inlineTable'
      }
    }
static get tableRowGroup(){
      return {
        display: 'tableRowGroup'
      }
    }
static get tableHeaderGroup(){
      return {
        display: 'tableHeaderGroup'
      }
    }
static get tableFooterGroup(){
      return {
        display: 'tableFooterGroup'
      }
    }
static get tableRow(){
      return {
        display: 'tableRow'
      }
    }
static get tableColumnGroup(){
      return {
        display: 'tableColumnGroup'
      }
    }
static get tableColumn(){
      return {
        display: 'tableColumn'
      }
    }
static get tableCell(){
      return {
        display: 'tableCell'
      }
    }
static get tableCaption(){
      return {
        display: 'tableCaption'
      }
    }
static get inherit(){
      return {
        display: 'inherit'
      }
    }
}
