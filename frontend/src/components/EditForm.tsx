import { useState } from 'react';

type BudgetItem = {
    id: number;
    description: string;
    amount: number;
    category: string;
    date: string;
};

type EditFormProps = {
    item: BudgetItem;
    onUpdate?: (updated: BudgetItem) => void;
};

export default function EditForm({ item, onUpdate }: EditFormProps) {
    const [form, setForm] = useState<BudgetItem>(item);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({
            ...prev,
            [name]: name === 'amount' ? +value : value,
        }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await fetch(`http://localhost:8080/budget-items/${form.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(form),
        });
        onUpdate?.(form);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label htmlFor="description">Description</label>
            <input id="description" name="description" value={form.description} onChange={handleChange} />

            <label htmlFor="amount">Amount</label>
            <input id="amount" name="amount" type="number" value={form.amount} onChange={handleChange} />

            <label htmlFor="category">Category</label>
            <input id="category" name="category" value={form.category} onChange={handleChange} />

            <label htmlFor="date">Date</label>
            <input id="date" name="date" type="date" value={form.date} onChange={handleChange} />

            <button type="submit">Submit</button>
        </form>
    );
}
