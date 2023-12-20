import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

 export function App() {


const [todoList, setTodoList] = useState(["todo1"])
const [tempTodo, setTempTodo] = useState()

  return (
    <div className="App">
        <div>{"Add To Do "}</div>
       <div className='center'>
        <input  onChange ={(event)=>{setTempTodo(event.target.value)}} type="text" />
        <button type='button' onClick={()=>{setTodoList((todoList)=>{return [...todoList,tempTodo]})}}>Ekle</button>
        <div>{"To Do List"}</div>
        <ul>

          {todoList.map((todo)=> {return <li>{todo} <button onClick={()=>{setTodoList(todoList.filter((t)=>{ return t!==todo}))}}>X</button ></li> })}
          
        </ul>
      </div>
    </div>
  );
};
export default App;
