<template>
    <!-- Happy Coding -->
    <div class="container">
    <!-- Calculator Result -->
        <header>
            <!-- <p>{{previousCalculatorValue}}  {{operator}}</p> -->
            <!-- <span v-if="previousoperator === '='">{{calculatedValue}}</span> -->
            <p> {{exp}}</p>
            {{ calculatorValue || 0 }}
        </header>

        <!-- Calculator buttons -->
        <div class="buttons">
            <!-- <div class="button" v-for="n in calculatorElements" :key="n" @click="action(n)">
                {{ n }}
            </div> -->
            <button class="button" v-for="n in calculatorElements" :key="n" @click="action(n)">
                <span v-if="n==='x2'">x<sup>2</sup></span>
                <span v-else-if="n==='sqrt(x)'"><span>&#8730;</span>x</span>
                <span v-else-if="n==='1/x'"><sup>1</sup>&frasl;<sub>x</sub></span>
                <span v-else-if="n==='/'"> &divide; </span>
                <span v-else-if="n==='*'"> &times; </span>
                <span v-else-if="n==='+-'"> &plusmn; </span>
                <span v-else>{{n}}</span>
            </button>
        </div>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: 'calculator',
    props: {
    msg: String
    },

    data() {
        return {
            calculatorValue: '',
            calculatorElements: ['%', 'CE', 'C', 'Del', '1/x', 'x2', 'sqrt(x)','/',7,8,9 ,'*',4,5,6,'-',1,2,3,'+','+-', 0,'.','='],
            operator: null,
            previousCalculatorValue: '',
            previousoperator : null,
            exp : '',
            calc: {
                "operand1" : '',
                "operand2" : '',
                "operator" : '',
            },
        }
    },
    methods: {
        set(){
            axios.post('http://localhost:8081/', {"operand1" : this.previousCalculatorValue,"operand2" : this.calculatorValue,"operator" : this.operator})
            .then(res => {this.calculatorValue = res.data,this.previousCalculatorValue = this.calculatorValue})
        },
        set2(){
            axios.post('http://localhost:8081/', {"operand1" : this.previousCalculatorValue,"operand2" : this.calculatorValue,"operator" : this.operator})
            .then(res => {this.previousCalculatorValue = res.data, this.calculatorValue = '', this.exp = this.previousCalculatorValue + (this.operator || '')})
        },
        set3(a, b, c){
            axios.post('http://localhost:8081/', {"operand1" : a,"operand2" : b,"operator" : c})
            .then(res => {this.calculatorValue = res.data})
        },
        action(n){
            
        if(n === 'C' || n === 'CE' || this.calculatorValue === 'E' ){
            this.exp = '';
        this.calculatorValue = '';
        this.previousCalculatorValue ='';
        this.operator = null;
        }
        if(n === 'Del') {
            if(this.calculatorValue != '' && this.previousoperator === null){
                this.calculatorValue = this.calculatorValue.slice(0,-1);
            }
        }
        /* Append value */
        if(!isNaN(n) || n === '.'){
            if(this.previousoperator === '='){
                this.calculatorValue = '';
                this.exp = '';
            }
            this.previousoperator = null;
            if((n === '.' && !this.calculatorValue.includes('.')))this.calculatorValue += n + '';
            else if(n != '.')this.calculatorValue += n + '';
            // this.calculatorValue += n + '';
        }
        /* Clear value */

        /* Percentage */
        if(n === '%'){
            if(this.calculatorValue === '' || this.calculatorValue === '0' )this.calculatorValue = '0';
            // else this.calculatorValue = this.calculatorValue / 100 + '';
            else {
                // this.set3(this.calculatorValue, "100", "/");
                this.set3(this.calculatorValue, this.calculatorValue, '%');
            }
            this.previousoperator = n;
        }

        /* Operators */
        if(['/','*','-','+'].includes(n)){
            if(this.operator !=null && this.calculatorValue != '' && this.previousCalculatorValue!=''){
                this.set2();
                this.operator = n;
                this.previousoperator = n;
            }
            else{
                this.operator = n;
                if(this.calculatorValue != '')this.previousCalculatorValue = this.calculatorValue;
                this.calculatorValue = '';
                this.previousoperator = n;
                this.exp = this.previousCalculatorValue + (this.operator || '');
            }
        }
        if(n === '+-'){
            if(this.calculatorValue!= '')this.calculatorValue = eval(this.calculatorValue + '*' + '-1');
        }
        if(n === '1/x'){
            if(this.calculatorValue === '' || this.calculatorValue === '0' ){
                this.calculatorValue = 'E';
            }
            // else this.calculatorValue = eval('1' + '/' + this.calculatorValue);
            else {
                // this.set3("1", this.calculatorValue, '/');
                this.set3(this.calculatorValue, this.calculatorValue, '1/x');
            }
        }
        if(n === 'x2'){
            if(this.calculatorValue === '' || this.calculatorValue === '0' )this.calculatorValue = '0';
            // else this.calculatorValue = eval(this.calculatorValue + '*' + this.calculatorValue);
            else {
                // this.set3(this.calculatorValue, this.calculatorValue, "*");
                this.set3(this.calculatorValue, this.calculatorValue, 'x2');
            }
        }
        if(n === 'sqrt(x)'){
            if(this.calculatorValue === '' || this.calculatorValue === '0' )this.calculatorValue = '0';
            // else this.calculatorValue = eval(Math.sqrt(this.calculatorValue));
            else {
                this.set3(this.calculatorValue, this.calculatorValue, 'sqrt(x)');
            }
        }
        /* Calculate result using the eval function */
        if(n === '='){
            // console.log(this.previousCalculatorValue + " " + this.operator + " " + this.calculatorValue)
            if(this.calculatorValue != '' && this.previousCalculatorValue === '' && this.operator === null)console.log("HI1");
            else if(this.calculatorValue === '' && this.previousCalculatorValue != '' && this.operator != null)this.calculatorValue = 'E', console.log("HI2");
            // if(this.calculatorValue != '' || this.previousCalculatorValue === '')this.calculatorValue = 'E';
            else if(this.previousoperator === '=')this.previousoperator = '=';
            else if(this.calculatorValue != ''  && this.operator != null && this.previousoperator != '='){
                if(this.previousCalculatorValue === '' || this.previouscalculatorValue === '0')this.previousCalculatorValue = '0';
                this.calc = {
                    "operand1" : this.previousCalculatorValue,
                    "operand2" : this.calculatorValue,
                    "operetor" : this.operator
                }
                this.exp = this.previousCalculatorValue + (this.operator || '')+ this.calculatorValue;
                    this.set();
                    this.operator = null;
                }
                // else{
                    //     console.log(this.previousCalculatorValue + " " + this.operator + " " + this.calculatorValue)
                    //     this.calculatorValue = 'E';
                    //     console.log("HI3");
                    // }
            this.previousoperator = n;
        }
    }
    }
}
</script> 
<style scoped>
.container {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    height: 100vh;
    background-color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 50 auto;
    width: 600px;
    height: 800px;
    background: rgb(214, 214, 214) ;
    border: 1px solid #141414;
}
header{
    overflow: hidden;
    display: flex;
    flex-direction: column;
    text-align:right;
    font-size:50px;
    font-weight: bold;
    padding: 5px;
    width: 100%;
    height: 25%;
    background: rgb(214, 214, 214);
}
.buttons {
    display: grid;
    grid-template-columns:repeat(4, calc((600px - 6px)/4 - .25px)) ;
    grid-gap: 2px;
}
.buttons .button {
    color: white;
    font-weight: bold;
    font-size: 30px;
    /*background: rgb(129, 129, 238);*/
    background: red;
    text-align: center;
    width: calc((600px - 6px)/4 - .25px);
    height: calc((640px)/6 - .25px);
    border: none;
}
.buttons .button:hover {
    color: red;
    cursor: pointer;
    background: rgb(214, 214, 214);
    border: none;
    transition:0.3s;
}
header p {
    font-size: 30px;
    color: #646464;
    text-align: left;
}
</style>