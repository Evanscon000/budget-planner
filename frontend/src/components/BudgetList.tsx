import { useEffect, useState } from 'react';

type BudgetItem = {
    id: number;
    description: string;
    amount: number;
    category: string;
    date: string;
};

export default function BudgetList() {
    const [items, setItems] = useState<BudgetItem[]>([]);

    useEffect(() => {
        const fetchItems = async () => {
            const res = await fetch('http://localhost:8080/budget-items');
            const data = await res.json();
            setItems(data);
        };

        fetchItems();
    }, []);

    return (
        <ul>
            {items.map(item => (
                <li key={item.id}>
                    <strong>{item.description}</strong> — ${item.amount}
                </li>
            ))}
        </ul>
    );
}
